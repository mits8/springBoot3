package com.example.springboot.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Meal")
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long meal_id;

    @Column(name ="mealName")
    private String mealName;

    @Column(name = "dayOfWeek")
    private String dayOfWeek;

    @ElementCollection
    @CollectionTable(name = "Breakfast", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "breakfast")
    private List<String> breakfast;

    @ElementCollection
    @CollectionTable(name = "Desert", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "desert")
    private List<String> desert;

    @ElementCollection
    @CollectionTable(name = "Lunch", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "lunch")
    private List<String> lunch;

    @ElementCollection
    @CollectionTable(name = "Snack", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "snack")
    private List<String> snack;
    @ElementCollection
    @CollectionTable(name = "Dinner", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "dinner")
    private List<String> dinner;

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Meal(Long meal_id, String mealName, String dayOfWeek, List<String> breakfast, List<String> desert, List<String> lunch, List<String> snack, List<String> dinner, List<Food> foods, Customer customer) {
        this.meal_id = meal_id;
        this.mealName = mealName;
        this.dayOfWeek = dayOfWeek;
        this.breakfast = breakfast;
        this.desert = desert;
        this.lunch = lunch;
        this.snack = snack;
        this.dinner = dinner;
        this.foods = foods;
        this.customer = customer;
    }

    public Meal() {

    }

    public void setMeal_id(Long mealId) {
        this.meal_id = meal_id;
    }

    public Long getMeal_id() {
        return meal_id;
    }

    public String getMealName() {
        return mealName;
    }
    public void setMealName(String name) {
        this.mealName = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<String> breakfast) {
        this.breakfast = breakfast;
    }

    public List<String> getDesert() {
        return desert;
    }

    public void setDesert(List<String> desert) {
        this.desert = desert;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public void setLunch(List<String> lunch) {
        this.lunch = lunch;
    }

    public List<String> getSnack() {
        return snack;
    }

    public void setSnack(List<String> snack) {
        this.snack = snack;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }
}

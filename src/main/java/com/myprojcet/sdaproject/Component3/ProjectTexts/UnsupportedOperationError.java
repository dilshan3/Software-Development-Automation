/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojcet.sdaproject.Component3.ProjectTexts;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sasini Randunuge
 */
public class UnsupportedOperationError {

    public static void main(String[] args) {
        String[] flowers = {"Ageratum", "Allium", "Poppy", "Catmint"};
        List<String> flowerList = Arrays.asList(flowers);
        flowerList.add("Celosia");

        Integer[] number = {1, 2, 3, 4, 5};
        List<Integer> numList = Arrays.asList(number);
        numList.add(6);

        Double[] dble = {10.5, 22.7, 334.2, 423.4, 51.4};
        List<Double> dbList = Arrays.asList(dble);
        dbList.add(63.4);

        Boolean[] bl = {true, false, true, true, true};
        List<Boolean> blList = Arrays.asList(bl);
        blList.add(true);

        Float[] ft = {10.5f, 22.7f, 334.2f, 423.4f, 51.4f};
        List<Float> ftList = Arrays.asList(ft);
        ftList.add(63.4f);

        Long[] lng = {12332252626L, 12332252626L, 12332252626L, 12332252626L, 12332252626L};
        List<Long> lgList = Arrays.asList(lng);
        lgList.add(12332252626L);

    }

}

package com.senla.carservice.view.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface IAction {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    void execute();
}

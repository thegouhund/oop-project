package com.project.controller;

import com.project.dao.AirlineDAO;
import com.project.model.Airline;

import java.util.ArrayList;

import static com.project.utils.DatabaseUtils.getConnection;

public class AirlineController extends Controller {
    private static final AirlineDAO airlineDAO = new AirlineDAO(getConnection());

    public static ArrayList<Airline> getAllAirline() {
        return airlineDAO.getAll();
    }

}

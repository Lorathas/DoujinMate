package org.lorathas.doujinmate.controller;

import org.hibernate.SessionFactory;

public class SettingsController {
    private SessionFactory sessionFactory;

    public SettingsController(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

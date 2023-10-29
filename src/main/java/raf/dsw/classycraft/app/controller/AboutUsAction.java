package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.AboutUsView;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction{

    public AboutUsAction() {
        putValue(NAME, "About Us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       new AboutUsView();


    }

}

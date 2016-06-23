package com.hubtel.mpos.Utilities;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.hubtel.mpos.Application.Application;

/**
 * Created by apple on 20/06/16.
 */

public class Typefacer {
    public Typeface getRoboBlack(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/Roboto-Black.ttf");
    }


    public Typeface squareRegular() {
        return Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/sqmarket-regular.ttf");
    }

    public Typeface Raleway(String mode) {

        Typeface typefacer = null;
        switch (mode) {

            case "black":

                typefacer = Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/Raleway-Black.ttf");
                break;
            case "thin":
                typefacer = Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/Raleway-Thin.ttf");
                break;
            case "light":
                typefacer = Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/Raleway-Light.ttf");
                break;
            case "medium":
                typefacer = Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/Raleway-Medium.ttf");
                break;
            case "extrabold":
                typefacer = Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/Raleway-ExtraBold.ttf");
                break;
        }

        return typefacer;

    }

    public Typeface digitalNormal() {
        return Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/digitalnormal.ttf");
    }

    public Typeface squareLight() {
        return Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/sqmarket-light.ttf");
    }

    public Typeface squareBold() {
        return Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/sqmarket-bold.ttf");
    }

    public Typeface squareMedium() {
        return Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/sqmarket-medium.ttf");
    }

    public Typeface getRoboMediumitalic(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/Roboto-MediumItalic.ttf");
    }

    public Typeface getRoboThinItalic(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/Roboto-ThinItalic.ttf");
    }

    public Typeface getRoboCondensedLight() {
        return Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/RobotoCondensed-Light.ttf");
    }

    public Typeface getRoboCondensedLghtItalic(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/RobotoCondensed-LightItalic.ttf");
    }

    public Typeface getRoboRegular(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/Roboto-Regular.ttf");
    }

    public Typeface getRoboRealThin() {
        return Typeface.createFromAsset(Application.getAppContext().getAssets(), "fonts/Roboto-Thin.ttf");
    }

    public Typeface getRoboCondensedRegular(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/RobotoCondensed-Regular.ttf");
    }

    public Typeface getRoboCondensedBold(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/RobotoCondensed-Bold.ttf");
    }

    public Typeface getBytes(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/Bytes.TTF");
    }

    public Typeface getRobThin(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/Roboto-Thin.ttf");
    }

    public Typeface getRoboBold(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/Roboto-Bold.ttf");
    }

    public Typeface getDigital7mono(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/digitalmono.ttf");
    }

    public Typeface getDigital7Italic(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/digitalitalic.ttf");
    }

    public Typeface getDigital7normal(AssetManager mgr) {
        return Typeface.createFromAsset(mgr, "fonts/digitalnormal.ttf");
    }
}
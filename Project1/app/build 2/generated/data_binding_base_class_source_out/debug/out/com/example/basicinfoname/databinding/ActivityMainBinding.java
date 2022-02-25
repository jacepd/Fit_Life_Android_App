// Generated by view binder compiler. Do not edit!
package com.example.basicinfoname.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.basicinfoname.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton buttonBMICalculator;

  @NonNull
  public final AppCompatButton buttonFitnessGoals;

  @NonNull
  public final AppCompatButton buttonHikes;

  @NonNull
  public final AppCompatButton buttonViewMyInfo;

  @NonNull
  public final AppCompatButton buttonWeather;

  @NonNull
  public final LinearLayout linearLayout0;

  @NonNull
  public final LinearLayout linearLayout1;

  @NonNull
  public final LinearLayout linearLayout10;

  @NonNull
  public final ImageView profilePhoto;

  private ActivityMainBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatButton buttonBMICalculator, @NonNull AppCompatButton buttonFitnessGoals,
      @NonNull AppCompatButton buttonHikes, @NonNull AppCompatButton buttonViewMyInfo,
      @NonNull AppCompatButton buttonWeather, @NonNull LinearLayout linearLayout0,
      @NonNull LinearLayout linearLayout1, @NonNull LinearLayout linearLayout10,
      @NonNull ImageView profilePhoto) {
    this.rootView = rootView;
    this.buttonBMICalculator = buttonBMICalculator;
    this.buttonFitnessGoals = buttonFitnessGoals;
    this.buttonHikes = buttonHikes;
    this.buttonViewMyInfo = buttonViewMyInfo;
    this.buttonWeather = buttonWeather;
    this.linearLayout0 = linearLayout0;
    this.linearLayout1 = linearLayout1;
    this.linearLayout10 = linearLayout10;
    this.profilePhoto = profilePhoto;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_BMI_Calculator;
      AppCompatButton buttonBMICalculator = ViewBindings.findChildViewById(rootView, id);
      if (buttonBMICalculator == null) {
        break missingId;
      }

      id = R.id.button_Fitness_Goals;
      AppCompatButton buttonFitnessGoals = ViewBindings.findChildViewById(rootView, id);
      if (buttonFitnessGoals == null) {
        break missingId;
      }

      id = R.id.button_hikes;
      AppCompatButton buttonHikes = ViewBindings.findChildViewById(rootView, id);
      if (buttonHikes == null) {
        break missingId;
      }

      id = R.id.button_view_my_info;
      AppCompatButton buttonViewMyInfo = ViewBindings.findChildViewById(rootView, id);
      if (buttonViewMyInfo == null) {
        break missingId;
      }

      id = R.id.button_weather;
      AppCompatButton buttonWeather = ViewBindings.findChildViewById(rootView, id);
      if (buttonWeather == null) {
        break missingId;
      }

      id = R.id.linearLayout0;
      LinearLayout linearLayout0 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout0 == null) {
        break missingId;
      }

      id = R.id.linearLayout1;
      LinearLayout linearLayout1 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout1 == null) {
        break missingId;
      }

      id = R.id.linearLayout10;
      LinearLayout linearLayout10 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout10 == null) {
        break missingId;
      }

      id = R.id.profile_photo;
      ImageView profilePhoto = ViewBindings.findChildViewById(rootView, id);
      if (profilePhoto == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, buttonBMICalculator,
          buttonFitnessGoals, buttonHikes, buttonViewMyInfo, buttonWeather, linearLayout0,
          linearLayout1, linearLayout10, profilePhoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
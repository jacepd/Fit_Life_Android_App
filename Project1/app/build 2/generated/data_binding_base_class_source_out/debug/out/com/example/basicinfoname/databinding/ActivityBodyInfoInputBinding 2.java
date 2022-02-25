// Generated by view binder compiler. Do not edit!
package com.example.basicinfoname.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
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

public final class ActivityBodyInfoInputBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton buttonSubmit;

  @NonNull
  public final EditText datePicker;

  @NonNull
  public final NumberPicker heightFeetNumberPicker;

  @NonNull
  public final NumberPicker heightInchesNumberPicker;

  @NonNull
  public final LinearLayout linearLayout1;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout5;

  @NonNull
  public final NumberPicker sexNumberPicker;

  @NonNull
  public final NumberPicker weightNumberPicker;

  private ActivityBodyInfoInputBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatButton buttonSubmit, @NonNull EditText datePicker,
      @NonNull NumberPicker heightFeetNumberPicker, @NonNull NumberPicker heightInchesNumberPicker,
      @NonNull LinearLayout linearLayout1, @NonNull LinearLayout linearLayout2,
      @NonNull LinearLayout linearLayout5, @NonNull NumberPicker sexNumberPicker,
      @NonNull NumberPicker weightNumberPicker) {
    this.rootView = rootView;
    this.buttonSubmit = buttonSubmit;
    this.datePicker = datePicker;
    this.heightFeetNumberPicker = heightFeetNumberPicker;
    this.heightInchesNumberPicker = heightInchesNumberPicker;
    this.linearLayout1 = linearLayout1;
    this.linearLayout2 = linearLayout2;
    this.linearLayout5 = linearLayout5;
    this.sexNumberPicker = sexNumberPicker;
    this.weightNumberPicker = weightNumberPicker;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBodyInfoInputBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBodyInfoInputBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_body_info_input, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBodyInfoInputBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_submit;
      AppCompatButton buttonSubmit = ViewBindings.findChildViewById(rootView, id);
      if (buttonSubmit == null) {
        break missingId;
      }

      id = R.id.datePicker;
      EditText datePicker = ViewBindings.findChildViewById(rootView, id);
      if (datePicker == null) {
        break missingId;
      }

      id = R.id.heightFeetNumberPicker;
      NumberPicker heightFeetNumberPicker = ViewBindings.findChildViewById(rootView, id);
      if (heightFeetNumberPicker == null) {
        break missingId;
      }

      id = R.id.heightInchesNumberPicker;
      NumberPicker heightInchesNumberPicker = ViewBindings.findChildViewById(rootView, id);
      if (heightInchesNumberPicker == null) {
        break missingId;
      }

      id = R.id.linearLayout1;
      LinearLayout linearLayout1 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout1 == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.linearLayout5;
      LinearLayout linearLayout5 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout5 == null) {
        break missingId;
      }

      id = R.id.sexNumberPicker;
      NumberPicker sexNumberPicker = ViewBindings.findChildViewById(rootView, id);
      if (sexNumberPicker == null) {
        break missingId;
      }

      id = R.id.weightNumberPicker;
      NumberPicker weightNumberPicker = ViewBindings.findChildViewById(rootView, id);
      if (weightNumberPicker == null) {
        break missingId;
      }

      return new ActivityBodyInfoInputBinding((RelativeLayout) rootView, buttonSubmit, datePicker,
          heightFeetNumberPicker, heightInchesNumberPicker, linearLayout1, linearLayout2,
          linearLayout5, sexNumberPicker, weightNumberPicker);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

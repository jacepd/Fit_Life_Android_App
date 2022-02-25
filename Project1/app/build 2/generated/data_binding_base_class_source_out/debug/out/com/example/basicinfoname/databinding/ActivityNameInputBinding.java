// Generated by view binder compiler. Do not edit!
package com.example.basicinfoname.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.basicinfoname.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityNameInputBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton buttonSubmit;

  @NonNull
  public final EditText firstNameInput;

  @NonNull
  public final TextView helloTextB;

  @NonNull
  public final EditText lastNameInput;

  @NonNull
  public final LinearLayout linearLayout1;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout3;

  private ActivityNameInputBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatButton buttonSubmit, @NonNull EditText firstNameInput,
      @NonNull TextView helloTextB, @NonNull EditText lastNameInput,
      @NonNull LinearLayout linearLayout1, @NonNull LinearLayout linearLayout2,
      @NonNull LinearLayout linearLayout3) {
    this.rootView = rootView;
    this.buttonSubmit = buttonSubmit;
    this.firstNameInput = firstNameInput;
    this.helloTextB = helloTextB;
    this.lastNameInput = lastNameInput;
    this.linearLayout1 = linearLayout1;
    this.linearLayout2 = linearLayout2;
    this.linearLayout3 = linearLayout3;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNameInputBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNameInputBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_name_input, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNameInputBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_submit;
      AppCompatButton buttonSubmit = ViewBindings.findChildViewById(rootView, id);
      if (buttonSubmit == null) {
        break missingId;
      }

      id = R.id.firstName_input;
      EditText firstNameInput = ViewBindings.findChildViewById(rootView, id);
      if (firstNameInput == null) {
        break missingId;
      }

      id = R.id.hello_textB;
      TextView helloTextB = ViewBindings.findChildViewById(rootView, id);
      if (helloTextB == null) {
        break missingId;
      }

      id = R.id.lastName_input;
      EditText lastNameInput = ViewBindings.findChildViewById(rootView, id);
      if (lastNameInput == null) {
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

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      return new ActivityNameInputBinding((RelativeLayout) rootView, buttonSubmit, firstNameInput,
          helloTextB, lastNameInput, linearLayout1, linearLayout2, linearLayout3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
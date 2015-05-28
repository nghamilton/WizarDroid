package org.codepond.wizardroid.extended;

import android.os.Parcel;
import android.os.Parcelable;

import org.codepond.wizardroid.WizardStep;

import java.util.ArrayList;

public class DynamicWizardFlow implements Parcelable {
    private ArrayList<Class<? extends WizardStep>> mSteps = new ArrayList<>();

    public DynamicWizardFlow addStep(Class<? extends WizardStep> step) {
        mSteps.add(step);
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.mSteps);
    }

    public DynamicWizardFlow() {
    }

    private DynamicWizardFlow(Parcel in) {
        this.mSteps = (ArrayList<Class<? extends WizardStep>>) in.readSerializable();
    }

    public static final Creator<DynamicWizardFlow> CREATOR = new Creator<DynamicWizardFlow>() {
        public DynamicWizardFlow createFromParcel(Parcel source) {
            return new DynamicWizardFlow(source);
        }

        public DynamicWizardFlow[] newArray(int size) {
            return new DynamicWizardFlow[size];
        }
    };

    public ArrayList<Class<? extends WizardStep>> getSteps() {
        return mSteps;
    }
}

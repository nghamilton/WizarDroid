package org.codepond.wizardroid.extended;

import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.WizardStep;

import java.util.ArrayList;
import java.util.List;

public class ExtendedWizardFlow extends WizardFlow {

    public ExtendedWizardFlow(List<StepMetaData> steps) {
        super(steps);
    }

    public static WizardFlow create(DynamicWizardFlow requestedFlow) {
        ArrayList<Class<? extends WizardStep>> steps = requestedFlow.getSteps();
        Builder b = new Builder();
        for (Class<? extends WizardStep> step : steps) {
            b.addStep(step);
        }

        //Now create the WizardFlow
        return b.create();
    }

 }

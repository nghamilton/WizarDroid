package org.codepond.wizardroid.extended;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.infrastructure.Bus;
import org.codepond.wizardroid.infrastructure.events.GoNextEvent;

public abstract class ExtendedWizardStep extends WizardStep {

    //Validation method - called before exiting, on click of next button
    public boolean validateInput()
    {
        return true;
    }


    /**
     * Notify the wizard that this step is completed
     */
    public final void notifyGoNext() {
        Bus.getInstance(getActivity().getClass()).post(new GoNextEvent(1, this));
    }

}

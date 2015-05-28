package org.codepond.wizardroid.infrastructure.events;

import org.codepond.wizardroid.WizardStep;

/**
 * Otto event triggered when a wizard step is either set as completed or incomplete
 */
public class GoNextEvent {
    private final int stepNumber;
    private WizardStep mWizardStep;

    public GoNextEvent(int stepNumber, WizardStep step) {
        this.stepNumber = stepNumber;
		this.mWizardStep = step;
    }

	public WizardStep getStep() {
		return mWizardStep;
	}
}

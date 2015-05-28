package org.codepond.wizardroid.extended;

import android.view.View;

import com.difarent.mobile.R;

import org.codepond.wizardroid.layouts.BasicWizardLayout;

public abstract class ExtendedWizardLayout extends BasicWizardLayout {


    public static final String WIZARD_FLOW_REQUEST = "WIZARD_FLOW";

    /**
     * Empty constructor for Fragment
     * You must have an empty constructor according to {@link #Fragment} documentation
     */
    public ExtendedWizardLayout() {
        super();
    }
    /**
     * Extended OnClick event that will perform a call on validation method and wont advance if false
     */
    @Override
    public void onClick(View v) {
        if(ExtendedWizard.isExtendedWizardStep(wizard.getCurrentStep())) {
            //perform additional functions if the wizardstep is an extended one
            if (v.getId() == org.codepond.wizardroid.R.id.wizard_next_button) {
                //Tell the wizard to go to next step only if input is validated
                if (!((ExtendedWizardStep) wizard.getCurrentStep()).validateInput())
                    return;
            }
        }

        //this will exit if at firs or last step and press cancel/done respectively
        super.onClick(v);
    }

    /**
     * Triggered when the wizard is completed: note that most difarent wizards manage this exit by overriding
     */
    @Override
    public void onWizardComplete() {
        super.onWizardComplete();
    }

    public void onWizardComplete(boolean requestActivityFinish)
    {
        super.onWizardComplete();

        //finish the activity if on the first or last extendedstep
        boolean firstOrLast = wizard.isFirstStep() || wizard.isLastStep();
        //firstOrLast will always tbe true, with modified version of wizardroid
        if (requestActivityFinish && ExtendedWizard.isExtendedWizardStep(wizard.getCurrentStep()) && firstOrLast)
        {
            getActivity().finish();
        }
    }

    /**
     * Event triggered after a step was changed, updating the button labels accordingly
     */
    @Override
    public void onStepChanged() {
        super.onStepChanged();
        updateWizardControls();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWizardControls();
    }

    /**
     * Updates the UI according to current step position
     */
    private void updateWizardControls() {
        //Disable the back button in the first step
        if (wizard.isFirstStep())
        {
            //If it is an extended FirstStep, then we use a cancel and exit for the button
            if(ExtendedWizard.isExtendedWizardStep(wizard.getFirstStep()))
            {
                getPreviousButton().setText(getResources().getString(R.string.action_wizard_cancel));
                getPreviousButton().setEnabled(true);
            }
            else
                getPreviousButton().setEnabled(false);
        }
        else
            getPreviousButton().setText(getBackButtonLabel());

        //Disable the next button if the step is marked as 'required' and is incomplete
        getNextButton().setEnabled(wizard.canGoNext());

        //Set different next button label based on the wizard position
        getNextButton().setText(wizard.isLastStep()
                ? getFinishButtonText()
                : getNextButtonLabel());
    }
}
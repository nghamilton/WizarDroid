package org.codepond.wizardroid.extended;

import android.support.v4.app.FragmentActivity;

import org.codepond.wizardroid.Wizard;
import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextManager;

public class ExtendedWizard extends Wizard {

    private ExtendedWizardCallbacks mCallBacks = null;


    /**
     * Constructor for Wizard
     *
     * @param wizardFlow     WizardFlow instance. See WizardFlow.Builder for more information on creating WizardFlow objects.
     * @param contextManager ContextManager instance would normally be {@link org.codepond.wizardroid.persistence.ContextManagerImpl}
     * @param callbacks      implementation of WizardCallbacks
     * @param activity       the hosting activity
     */
    public ExtendedWizard(WizardFlow wizardFlow, ContextManager contextManager, ExtendedWizardCallbacks callbacks, FragmentActivity activity) {
        super(wizardFlow, contextManager, callbacks, activity);

        mCallBacks = callbacks;
    }

    public interface ExtendedWizardCallbacks extends WizardCallbacks {

        void onWizardCancel();

    }

    /**
     * Takes the wizard one step back, exists if the step is an extended first step
     */
    public void goBack() {
        super.goBack();
        //If it is the first step in an extended class, we call onExit
        if(isFirstStep() && ExtendedWizard.isExtendedWizardStep(getCurrentStep()))
        {
            mCallBacks.onWizardCancel();
        }
    }

    public static boolean isExtendedWizardStep(WizardStep currentStep) {
        return ExtendedWizardStep.class.isAssignableFrom(currentStep.getClass());
    }
}

package eapli.ecourse.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Setting implements ValueObject {

    @Enumerated(EnumType.STRING)
    private SettingType feedbackSetting;

    @Enumerated(EnumType.STRING)
    private SettingType gradeSetting;

    protected Setting() {
        //ORM only
    }

    protected Setting(SettingType feedbackSetting, SettingType gradeSetting) {
        Preconditions.noneNull(feedbackSetting, gradeSetting);
        setFeedbackSetting(feedbackSetting);
        setGradeSetting(gradeSetting);
    }

    protected void setFeedbackSetting(SettingType feedbackSetting) {
        this.feedbackSetting = feedbackSetting;
    }

    protected void setGradeSetting(SettingType gradeSetting) {
        this.gradeSetting = gradeSetting;
    }
}

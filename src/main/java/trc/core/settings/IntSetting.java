package trc.core.settings;

public class IntSetting extends Setting {
    public int value;
    public int defaultValue;
    public int minValue;
    public int maxValue;
    public int milliStep;
    public boolean isInputField;

    public IntSetting(String id, String name, int initialValue, int min, int max, int milliStep, boolean inputField) {
        super(id, name);
        this.value = initialValue;
        this.defaultValue = initialValue;
        this.minValue = min;
        this.maxValue = max;
        this.milliStep = milliStep;
        this.isInputField = inputField;
    }

    @Override
    public void reset() { this.value = this.defaultValue; }

    @Override
    public boolean isDefault() { return this.value == this.defaultValue; }
}

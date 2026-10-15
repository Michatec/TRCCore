package trc.core.settings;

public class BoolSetting extends Setting {
    public boolean value;

    public boolean defaultValue;

    public BoolSetting(String id, String name, boolean initialValue) {
        super(id, name);
        this.value = initialValue;
        this.defaultValue = initialValue;
    }

    @Override
    public void reset() { this.value = this.defaultValue; }

    @Override
    public boolean isDefault() { return this.value == this.defaultValue; }
}
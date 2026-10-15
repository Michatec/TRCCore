package trc.core.settings;

public class StringSetting extends Setting {
    public static final int MAX_LENGTH = 128;

    public String value;
    public String defaultValue;
    public int maxLength;

    public boolean sensitive;

    public StringSetting(String id, String name, String initialValue) {
        this(id, name, initialValue, MAX_LENGTH);
    }

    public StringSetting(String id, String name, String initialValue, int maxLength) {
        super(id, name);
        this.value = initialValue == null ? "" : initialValue;
        this.defaultValue = this.value;
        this.maxLength = Math.clamp(maxLength, 1, MAX_LENGTH);
    }

    public StringSetting sensitive() {
        this.sensitive = true;
        return this;
    }

    @Override
    public void reset() { this.value = this.defaultValue; }

    @Override
    public boolean isDefault() { return this.value.equals(this.defaultValue); }
}

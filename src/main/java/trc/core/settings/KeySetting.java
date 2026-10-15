package trc.core.settings;

public class KeySetting extends Setting {
    public static final String UNBOUND = "key.keyboard.unknown";

    public String value;
    public String defaultValue;

    public KeySetting(String id, String name, String initialKey) {
        super(id, name);
        this.value = initialKey == null || initialKey.isBlank() ? UNBOUND : initialKey;
        this.defaultValue = this.value;
    }

    public boolean isUnbound() { return UNBOUND.equals(this.value); }

    @Override
    public void reset() { this.value = this.defaultValue; }

    @Override
    public boolean isDefault() { return this.value.equals(this.defaultValue); }
}

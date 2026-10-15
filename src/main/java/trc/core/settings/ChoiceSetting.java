package trc.core.settings;

import java.util.List;

public class ChoiceSetting extends Setting {
    public List<String> values;
    public int index;
    public int defaultIndex;

    public ChoiceSetting(String id, String name, List<String> values, int initialIndex) {
        super(id, name);
        this.values = values;
        this.index = initialIndex;
        this.defaultIndex = initialIndex;
    }

    @Override
    public void reset() { this.index = this.defaultIndex; }

    @Override
    public boolean isDefault() { return this.index == this.defaultIndex; }
}
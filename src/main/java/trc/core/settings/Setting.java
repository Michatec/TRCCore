package trc.core.settings;

import java.util.Locale;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;

public abstract class Setting {
    private static final Pattern VALID_ID = Pattern.compile("[a-z0-9_-]+");

    public String name;

    public String id;

    public Setting(String id, String name) {
        this.name = name;
        this.id = validateId(id, name);
    }

    public abstract void reset();

    public abstract boolean isDefault();

    private static @NotNull String validateId(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "Setting '" + name + "' has no id. Every setting needs one: lowercase a-z, 0-9, underscore or hyphen.");
        }

        String trimmed = id.trim().toLowerCase(Locale.ROOT);

        if (!VALID_ID.matcher(trimmed).matches()) {
            throw new IllegalArgumentException(
                    "Setting '" + name + "' has the invalid id '" + id + "'. Ids may only contain a-z, 0-9, underscore and hyphen.");
        }

        return trimmed;
    }
}

package com.teammetallurgy.atum.utils;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class AtumUtils {

    /**
     * Turns a registry name into a unlocalized name.
     *
     * Example: Turns example_name into exampleName
     *
     * @param name the registry name to convert into the unlocalized name.
     */
    public static String toUnlocalizedName(String name) {
        return StringUtils.uncapitalize(WordUtils.capitalize(name, '_')).replace("_", "");
    }

    /**
     * Turns a unloalized name into a registry name.
     *
     * Example: Turns exampleName into example_name
     *
     * @param name the unlocalized name to convert into registry name.
     */
    public static String toRegistryName(String name) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
    }
}
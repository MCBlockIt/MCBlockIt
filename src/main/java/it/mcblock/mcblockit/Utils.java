package it.mcblock.mcblockit;

public class Utils {
    public static String combineSplit(String[] args, String delimiter, int start, int finish) {
        if (args.length < start) {
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        for (int x = start; (x < args.length) && (x <= finish); x++) {
            if (builder.length() > 0) {
                builder.append(delimiter);
            }
            builder.append(args[x]);
        }
        return builder.toString();
    }
}

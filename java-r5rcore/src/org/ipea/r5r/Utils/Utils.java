package org.ipea.r5r.Utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.conveyal.r5.api.util.LegMode;
import com.conveyal.r5.api.util.TransitModes;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;

public class Utils {

    static public boolean verbose = true;

    public static EnumSet<LegMode> setLegModes(String modes) {
        EnumSet<LegMode> legModes = EnumSet.noneOf(LegMode.class);

        String[] modesArray = modes.split(";");
        if (!modes.equals("") & modesArray.length > 0) {
            for (String mode : modesArray) {
                legModes.add(LegMode.valueOf(mode));
            }
        }

        return legModes;
    }

    public static EnumSet<TransitModes> setTransitModes(String modes) {
        EnumSet<TransitModes> transitModes = EnumSet.noneOf(TransitModes.class);

        String[] modesArray = modes.split(";");
        if (!modes.equals("") & modesArray.length > 0) {
            for (String mode : modesArray) {
                transitModes.add(TransitModes.valueOf(mode));
            }
        }

        return transitModes;
    }

    public static int getSecondsFromMidnight(String departureTime) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date reference = dateFormat.parse("00:00:00");
        Date date = dateFormat.parse(departureTime);
        return (int) ((date.getTime() - reference.getTime()) / 1000L);
    }

    public static void setLogMode(String mode, boolean verbose) {
        Utils.verbose = verbose;

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        Logger logger = loggerContext.getLogger("com.conveyal.r5");
        logger.setLevel(Level.valueOf(mode));

        logger = loggerContext.getLogger("com.conveyal.osmlib");
        logger.setLevel(Level.valueOf(mode));

        logger = loggerContext.getLogger("com.conveyal.gtfs");
        logger.setLevel(Level.valueOf(mode));

        logger = loggerContext.getLogger("com.conveyal.r5.profile.ExecutionTimer");
        logger.setLevel(Level.valueOf(mode));

        logger = loggerContext.getLogger("org.ipea.r5r.R5RCore");
        logger.setLevel(Level.valueOf(mode));

        logger = loggerContext.getLogger("org.ipea.r5r.PathOptionsTable");
        logger.setLevel(Level.valueOf(mode));
    }
}

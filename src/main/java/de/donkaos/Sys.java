/*
 * Copyright (c) 2022.
 * This code was written by Donkaos
 */

package de.donkaos;

import de.donkaos.Exception.OSNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sys {     // TODO GetOS()
    public static class wait extends Wait{}
    private static String prefix = "SYS";
    private static boolean colorsEnabled = true;
    private static boolean debugging = false;
    public static boolean getDebugging(){
        return debugging;
    }
    public static boolean isColorsEnabled() {
        return colorsEnabled;
    }
    public static void setDebugging(boolean debugging){
        Sys.debugging = debugging;
    }
    public static void useColors(boolean colorsEnabled){
        Sys.colorsEnabled = colorsEnabled;
    }
    public static void useWebsocket(boolean websockets){
        WS.websocket = websockets;
    }
    public static void setPrefix(String prefix){
        Sys.prefix = prefix;
    }
    public static String getPrefix() {
        return "[" + prefix + "]";
    }

    /*public static OS getOS() throws OSNotFoundException {
        String os = System.getProperty("os.name");
        System.out.println("Using System Property: " + os);
        switch (os){
            case "Windows 10":
                return OS.Windows_10;
            case "Windows 11":
                return OS.Windows_11;
            default:
                throw new OSNotFoundException(os);
        }
    }*/

    private static String getTimeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void debug(String msg){
        if (!debugging){
            return;
        }
        if (isColorsEnabled()){
            System.out.println(Colors.YELLOW + "["+getTimeStamp()+"] " + getPrefix() + " " + Colors.BLACK + Colors.YELLOW_BACKGROUND + "[DEBUG]" + Colors.YELLOW + " " + msg + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [DEBUG] " + msg);
    }

    public static void log(String msg){
        if (isColorsEnabled()){
            System.out.println(Colors.BLUE_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [LOG] " + msg + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [LOG] " + msg);
    }

    public static void success(String msg){
        if (isColorsEnabled()){
            System.out.println(Colors.GREEN_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [SUCCESS] " + msg + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [SUCCESS] " + msg);
    }

    public static void info(String msg){
        if (isColorsEnabled()){
            System.out.println(Colors.CYAN_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [INFO] " + msg + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [INFO] " + msg);
    }

    public static void init(String msg){
        if (isColorsEnabled()){
            System.out.println(Colors.PURPLE_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [INIT] " + msg + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [INIT] " + msg);
    }

    public static void warn(String msg){
        if (isColorsEnabled()){
            System.out.println(Colors.YELLOW_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + msg + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + msg);
    }

    public static void warn(String header, String body){
        if (isColorsEnabled()){
            System.out.println(Colors.YELLOW_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + header + "\n" + body + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + header + "\n" + body);
    }


    public static void error(String msg){
        if (isColorsEnabled()){
            System.out.println(Colors.RED_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + msg + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + msg);
    }

    public static void error(String header, String body){
        if (isColorsEnabled()){
            System.out.println(Colors.RED_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + header + "\n" + body + Colors.RESET);
            return;
        }
        System.out.println("["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + header + "\n" + body);
    }



    public static class Colors{
        // Reset
        public static final String RESET = "\033[0m";  // Text Reset

        // Regular Colors
        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        public static final String GREEN = "\033[0;32m";   // GREEN
        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
        public static final String PURPLE = "\033[0;35m";  // PURPLE
        public static final String CYAN = "\033[0;36m";    // CYAN
        public static final String WHITE = "\033[0;37m";   // WHITE

        // Bold
        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

        // Underline
        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
        public static final String RED_UNDERLINED = "\033[4;31m";    // RED
        public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
        public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
        public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
        public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
        public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
        public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

        // Background
        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
        public static final String RED_BACKGROUND = "\033[41m";    // RED
        public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
        public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
        public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
        public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

        // High Intensity
        public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        public static final String RED_BRIGHT = "\033[0;91m";    // RED
        public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

        // Bold High Intensity
        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

        // High Intensity backgrounds
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }

}

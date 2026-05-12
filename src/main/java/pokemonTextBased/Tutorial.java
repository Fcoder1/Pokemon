package pokemonTextBased;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Tutorial {

    private static final String DIVIDER = "=".repeat(70);
    private static final String THIN    = "-".repeat(70);

    private static final String[][] PAGES = {
        {
            "PAGE 1 / 5  —  MAP & MOVEMENT",
            THIN,
            "Open the map from the Play Menu with [M].",
            "",
            "On the map screen:",
            "  [P] Pallet Town       [V] Viridian City    [W] Pewter City",
            "  [C] Cerulean City     [M] Vermillion City  [L] Lavender Town",
            "  [S] Saffron City      [F] Fuchsia City     [I] Cinnabar Island",
            "  [T] Indigo Plateau    [R] Rocketopolis     [D] Celadon City",
            "  [G] Vaughan District",
            "",
            "  Enter a number 1-25 to travel a route and encounter wild Pokemon.",
            "  [B] to go back.",
        },
        {
            "PAGE 2 / 5  —  BATTLE BASICS",
            THIN,
            "Battles are turn-based. On your turn you choose one action:",
            "",
            "  [F] Fight  — Pick one of your Pokemon's moves (1-4).",
            "  [B] Bag    — Use a Potion, Pokeball, or other item.",
            "  [S] Switch — Send out a different Pokemon from your party.",
            "  [I] Info   — View AI engine analysis and match-up ratings.",
            "  [R] Run    — Flee (wild battles only).",
            "",
            "Inside the move list:",
            "  [1-4] Select a move   [B] Back to action menu",
            "",
            "When your Pokemon faints you must switch to another one.",
            "If all your Pokemon faint you white out and are healed at the",
            "nearest Pokemon Center.",
        },
        {
            "PAGE 3 / 5  —  HINTS & AI ENGINE",
            THIN,
            "The game has a built-in AI engine that can help you play:",
            "",
            "  NO HINTS           — No assistance shown.",
            "  SHOW EFFECTIVENESS — Type matchup colour-coding on moves.",
            "  SHOW ENGINE ANALYSIS (default)",
            "                     — Highlights the engine's recommended move",
            "                       and suggests when to switch Pokemon.",
            "",
            "Change hint mode any time via [O] Options in the Play Menu.",
            "",
            "You can also open the battle simulation tool with [I] Info",
            "during a battle to see estimated win probabilities.",
        },
        {
            "PAGE 4 / 5  —  BAG, BADGES & REPUTATION",
            THIN,
            "Bag:  Holds Pokedollars, Battle Points (BP), items and notes.",
            "      Buy items at Pokemarts inside cities.",
            "      Special items, stocks, loans, and a casino are also available!",
            "",
            "Badges: Defeat gym leaders to earn badges and raise your level cap.",
            "        Check your badges from the Play Menu with [V] View Badges.",
            "",
            "Reputation: Rises when you beat strong trainers and falls when you",
            "            lose. It affects what content you can access.",
            "",
            "Pokedex: Automatically records every Pokemon you encounter.",
            "         Browse it from within any city's options.",
        },
        {
            "PAGE 5 / 5  —  SAVING & LOADING",
            THIN,
            "The game uses save slots (0-5).",
            "",
            "  Saving:  Open [O] Options from the Play Menu and choose Save.",
            "           Or visit any Pokemon Center — they offer free healing",
            "           and a save option.",
            "",
            "  Loading: At startup you are shown all save slots.",
            "           Select your slot number to continue where you left off.",
            "           Select an empty slot to start a new game.",
            "",
            "Tip: Use the alias 'pkm' described in the README so your save",
            "     files are always found in the right directory.",
        }
    };

    // -------------------------------------------------------------------------
    // Help Centre — sub-menu + keyword search
    // -------------------------------------------------------------------------
    public static void showHelp(Scanner sc1) {
        label:
        while (true) {
            System.out.println(DIVIDER);
            System.out.println("  HELP CENTRE");
            System.out.println(THIN);
            System.out.println("  [G] Full Guide   [S] Search   [B] Back");
            System.out.println(DIVIDER);
            String choice = sc1.nextLine().trim().toUpperCase();
            switch (choice) {
                case "G" -> showHowToPlay(sc1);
                case "S" -> showHelpSearch(sc1);
                case "B" -> { break label; }
                default  -> System.out.println("Invalid input. Please enter [G], [S], or [B].");
            }
        }
    }

    public static void showHelpSearch(Scanner sc1) {
        System.out.println(DIVIDER);
        System.out.println("  HELP SEARCH  —  type a keyword and press ENTER.  [Q] to quit.");
        System.out.println(THIN);
        System.out.println("  Examples: heal  gym  badge  save  bag  route  rep  casino");
        System.out.println(DIVIDER);
        while (true) {
            System.out.print("  Search > ");
            String query = sc1.nextLine().trim().toLowerCase();
            if (query.equals("q") || query.isEmpty()) return;

            System.out.println(DIVIDER);
            if (matchesAny(query, "heal", "recover", "pokemon center", "pokecenter", "hp")) {
                System.out.println("  HOW TO HEAL YOUR POKEMON");
                System.out.println(THIN);
                System.out.println("  Visit any Pokemon Center — it's free!");
                System.out.println("  On the Map [M], go to a city and press [P] to enter the Pokemon Center.");
                System.out.println("  Cities with Pokemon Centers:");
                System.out.println("    [V] Viridian City    [W] Pewter City      [C] Cerulean City");
                System.out.println("    [M] Vermilion City   [L] Lavender Town    [D] Celadon City");
                System.out.println("    [S] Saffron City     [F] Fuchsia City     [I] Cinnabar Island");
                System.out.println("    [T] Indigo Plateau   [R] Rocketopolis     [G] Vaughan District");
                System.out.println("  You can also heal at home: [P] Pallet Town → [H] Home → [R] Rest.");
                System.out.println("  If all your Pokemon faint, you're automatically healed at the nearest center.");
            } else if (matchesAny(query, "gym", "badge", "leader", "all gyms")) {
                System.out.println("  GYM LEADERS & BADGES");
                System.out.println(THIN);
                System.out.println("  Defeat gym leaders to earn badges and raise your level cap.");
                System.out.println("  In each city, press [G] to go to the gym, then [E] to enter and battle.");
                System.out.println();
                System.out.println("  City Key  City             Leader     Type");
                System.out.println("  --------  ---------------  ---------  ----------");
                System.out.println("  [W]       Pewter City      Brock      Rock");
                System.out.println("  [C]       Cerulean City    Misty      Water");
                System.out.println("  [M]       Vermilion City   Lt. Surge  Electric");
                System.out.println("  [D]       Celadon City     Erika      Grass");
                System.out.println("  [F]       Fuchsia City     Koga       Poison");
                System.out.println("  [S]       Saffron City     Sabrina    Psychic");
                System.out.println("  [I]       Cinnabar Island  Blaine     Fire");
                System.out.println("  [V]       Viridian City    Blue       Mixed");
                System.out.println("  [R]       Rocketopolis     Giovanni   Mixed");
                System.out.println("  [G]       Vaughan District Vaughan    Mixed");
                System.out.println();
                System.out.println("  Check your earned badges from the Play Menu with [V] View Badges.");
            } else if (matchesAny(query, "brock", "pewter", "rock")) {
                System.out.println("  PEWTER CITY GYM  —  Brock  (Rock-type)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [W] Pewter City → [G] Gym → [E] Enter & battle Brock.");
                System.out.println("  Tip: Grass or Water moves are super-effective against Rock-types.");
            } else if (matchesAny(query, "misty", "cerulean", "water")) {
                System.out.println("  CERULEAN CITY GYM  —  Misty  (Water-type)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [C] Cerulean City → [G] Gym → [E] Enter & battle Misty.");
                System.out.println("  Tip: Electric or Grass moves are super-effective against Water-types.");
            } else if (matchesAny(query, "surge", "lt. surge", "vermilion", "electric")) {
                System.out.println("  VERMILION CITY GYM  —  Lt. Surge  (Electric-type)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [M] Vermilion City → [G] Gym → [E] Enter & battle Lt. Surge.");
                System.out.println("  Tip: Ground moves are super-effective against Electric-types.");
            } else if (matchesAny(query, "erika", "celadon", "grass")) {
                System.out.println("  CELADON CITY GYM  —  Erika  (Grass-type)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [D] Celadon City → [G] Gym → [E] Enter & battle Erika.");
                System.out.println("  Tip: Fire, Flying, or Ice moves are super-effective against Grass-types.");
            } else if (matchesAny(query, "koga", "fuchsia", "poison")) {
                System.out.println("  FUCHSIA CITY GYM  —  Koga  (Poison-type)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [F] Fuchsia City → [G] Gym → [E] Enter & battle Koga.");
                System.out.println("  Tip: Ground or Psychic moves are super-effective against Poison-types.");
            } else if (matchesAny(query, "sabrina", "saffron", "psychic")) {
                System.out.println("  SAFFRON CITY GYM  —  Sabrina  (Psychic-type)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [S] Saffron City → [G] Gym → [E] Enter & battle Sabrina.");
                System.out.println("  Tip: Dark, Ghost, or Bug moves are super-effective against Psychic-types.");
            } else if (matchesAny(query, "blaine", "cinnabar", "fire")) {
                System.out.println("  CINNABAR ISLAND GYM  —  Blaine  (Fire-type)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [I] Cinnabar Island → [G] Gym → [E] Enter & battle Blaine.");
                System.out.println("  Tip: Water, Rock, or Ground moves are super-effective against Fire-types.");
            } else if (matchesAny(query, "blue", "viridian")) {
                System.out.println("  VIRIDIAN CITY GYM  —  Blue  (Mixed)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [V] Viridian City → [G] Gym → [E] Enter & battle Blue.");
            } else if (matchesAny(query, "giovanni", "rocketopolis")) {
                System.out.println("  ROCKETOPOLIS GYM  —  Giovanni  (Mixed)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [R] Rocketopolis → [G] Gym → [E] Enter & battle Giovanni.");
            } else if (matchesAny(query, "vaughan")) {
                System.out.println("  VAUGHAN DISTRICT GYM  —  Vaughan  (Mixed)");
                System.out.println(THIN);
                System.out.println("  Map [M] → [G] Vaughan District → [G] Gym → [E] Enter & battle Vaughan.");
            } else if (matchesAny(query, "save", "saving", "load")) {
                System.out.println("  HOW TO SAVE & LOAD");
                System.out.println(THIN);
                System.out.println("  Save:  [O] Options (from any city or the Play Menu) → [S] Save.");
                System.out.println("         You can also save at any Pokemon Center after healing.");
                System.out.println("  Load:  At game startup you are shown all save slots (0–5).");
                System.out.println("         Enter a slot number to continue, or an empty slot to start fresh.");
            } else if (matchesAny(query, "bag", "item", "potion", "pokeball", "ball", "medicine")) {
                System.out.println("  HOW TO USE YOUR BAG / ITEMS");
                System.out.println(THIN);
                System.out.println("  Open your bag:  [O] Options → [B] Bag.");
                System.out.println("  Buy items:      Go to any city → [M] Pokemart.");
                System.out.println("  In battle:      Press [B] Bag to use Potions, Pokeballs, etc.");
            } else if (matchesAny(query, "shop", "buy", "pokemart", "mart", "store")) {
                System.out.println("  HOW TO SHOP / POKEMART");
                System.out.println(THIN);
                System.out.println("  Go to any city on the map and press [M] to enter the Pokemart.");
                System.out.println("  Cities with Pokemarts: Viridian, Pewter, Cerulean, Vermilion,");
                System.out.println("  Lavender, Celadon, Saffron, Fuchsia, Cinnabar, and more.");
            } else if (matchesAny(query, "pokedex", "dex")) {
                System.out.println("  HOW TO VIEW THE POKEDEX");
                System.out.println(THIN);
                System.out.println("  [O] Options → [P] Pokedex.");
                System.out.println("  Every Pokemon you encounter is automatically recorded.");
            } else if (matchesAny(query, "party", "switch", "switch pokemon", "team")) {
                System.out.println("  HOW TO MANAGE YOUR PARTY");
                System.out.println(THIN);
                System.out.println("  [O] Options → [V] Party to view, reorder, or check your Pokemon.");
                System.out.println("  In battle, press [S] Switch to send out a different Pokemon.");
            } else if (matchesAny(query, "level", "level cap", "cap", "exp", "experience")) {
                System.out.println("  LEVEL CAPS");
                System.out.println(THIN);
                System.out.println("  Your Pokemon cannot exceed the current level cap.");
                System.out.println("  Defeat gym leaders to earn badges and raise the cap.");
                System.out.println("  Check your badges: Play Menu [V] View Badges,");
                System.out.println("  or [O] Options → More Options [M] → Trainer Card [T].");
            } else if (matchesAny(query, "wild", "route", "catch", "encounter", "pokemon")) {
                System.out.println("  HOW TO FIND WILD POKEMON");
                System.out.println(THIN);
                System.out.println("  Map [M] → type a route number 1–25 to explore a route.");
                System.out.println("  Walking through routes triggers random wild Pokemon encounters.");
                System.out.println("  In battle use a Pokeball ([B] Bag) to try to catch wild Pokemon.");
            } else if (matchesAny(query, "reputation", "rep", "rank")) {
                System.out.println("  REPUTATION");
                System.out.println(THIN);
                System.out.println("  Your reputation rises when you beat strong trainers.");
                System.out.println("  It falls when you lose battles.");
                System.out.println("  Higher reputation unlocks tougher content and better rewards.");
            } else if (matchesAny(query, "casino", "game corner", "slot", "celadon game")) {
                System.out.println("  CASINO / GAME CORNER");
                System.out.println(THIN);
                System.out.println("  Map [M] → [D] Celadon City → [G] Game Corner.");
            } else {
                System.out.println("  No results for '" + query + "'.");
                System.out.println("  Try keywords like: heal, gym, badge, save, bag, route, rep, casino");
            }
            System.out.println(DIVIDER);
        }
    }

    /** Returns true if the query contains any of the given keywords. */
    private static boolean matchesAny(String query, String... keywords) {
        for (String kw : keywords) {
            if (query.contains(kw)) return true;
        }
        return false;
    }

    public static void showHowToPlay(Scanner sc1) {
        for (int i = 0; i < PAGES.length; i++) {
            System.out.println(DIVIDER);
            for (String line : PAGES[i]) {
                System.out.println("  " + line);
            }
            System.out.println(DIVIDER);
            if (i < PAGES.length - 1) {
                System.out.println("[ENTER] Next page   [Q] Quit guide");
                String input = sc1.nextLine().trim().toUpperCase();
                if (input.equals("Q")) return;
            } else {
                System.out.println("[ENTER] Close guide");
                sc1.nextLine();
            }
        }
    }

    public static void playTutorialBattle(Scanner sc1) throws InterruptedException, ExecutionException {
        if (User.hasSeenTutorialBattle) return;
        User.hasSeenTutorialBattle = true;
        // We've already explained controls, so skip the in-battle tutorial banner
        User.hasSeenBattleTutorial = true;

        System.out.println(DIVIDER);
        System.out.println("  PROFESSOR OAK: Before you head off, let's do a quick practice battle!");
        System.out.println("  A wild Rattata has wandered into the lab — perfect for training.");
        System.out.println(DIVIDER);
        Thread.sleep((long) (User.textSpeed * 0.75));

        System.out.println("  In battle you have five options:");
        System.out.println("  [F] Fight  — Select a move and attack. Try pressing [F] then [1].");
        System.out.println("  [B] Bag    — Open your bag to use a Potion or throw a Pokeball.");
        System.out.println("  [S] Switch — Swap to a different Pokemon in your party.");
        System.out.println("  [I] Info   — View AI battle analysis and match-up ratings.");
        System.out.println("  [R] Run    — Flee from a wild battle at any time.");
        System.out.println(THIN);
        System.out.println("  Tip: Try [I] Info during the battle to see move ratings.");
        System.out.println("  Tip: Try [R] Run when you want to escape — no penalty in the wild!");
        System.out.println(DIVIDER);
        Game.pressEnterToContinue(sc1);

        Pokemon rattata = new Pokemon(Species.getSpecies("Rattata"), 3, false);
        Encounter.enterWildPkmBattle(rattata, sc1);

        System.out.println(DIVIDER);
        System.out.println("  PROFESSOR OAK: Excellent work! You're ready for your journey.");
        System.out.println("  Remember, you can read the full guide any time with [H] in the Play Menu.");
        System.out.println(DIVIDER);
        Thread.sleep((long) (User.textSpeed * 0.5));
        Game.pressEnterToContinue(sc1);
    }
}

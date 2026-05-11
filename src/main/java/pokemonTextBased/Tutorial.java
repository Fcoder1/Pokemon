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

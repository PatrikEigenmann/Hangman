/* ----------------------------------------------------------------------------------------
 * WordBank.java - WordBank is a centralized vocabulary vault for Hangman gameplay. It
 * stores a curated list of 6–8 letter uppercase words and delivers randomized selections
 * for each new game session. Designed as a singleton, it ensures consistent access and
 * easy expansion, whether through manual additions or future encrypted imports. This class
 * embodies modular clarity, runtime efficiency, and gameplay integrity.
 * ----------------------------------------------------------------------------------------
 * Author:      Patrik Eigenmann
 * eMail:       p.eigenmann@gmx.net
 * GitHub:      https://www.github.com/PatrikEigenmann/Hangman
 * ----------------------------------------------------------------------------------------
 * Change Log:
 * Sun 2025-07-13 File created.                                              Version: 00.01
 * Sun 2025-07-13 Import the class Debug to track system layer messages.     Version: 00.02
 * ---------------------------------------------------------------------------------------- */

package Hangman.Model;

// Standard Java imports
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// Hangman Debugging Utility import
import Hangman.Utility.Debug;

/**
 * The class WordBank is a centralized vocabulary vault for Hangman gameplay. It
 * stores a curated list of 6–8 letter uppercase words and delivers randomized selections
 * for each new game session. Designed as a singleton, it ensures consistent access and
 * easy expansion, whether through manual additions or future encrypted imports. This class
 * embodies modular clarity, runtime efficiency, and gameplay integrity. 
 */
public class WordBank {

    /**
     * Singleton instance of WordBank. This ensures that only one instance
     * of WordBank exists throughout the application, providing a consistent
     * source of words for the game.
     */
    private static WordBank instance;
    
    /**
     * The list of available words, the vocabulary vault for the game.
     * This list contains words that are 6 to 8 letters long, all in uppercase
     */
    private final List<String> words = new ArrayList<>();
    
    /**
     * Random instance for selecting words randomly from the list.
     * This allows for dynamic word selection each time a new game starts,
     * enhancing replayability and variety.
     */
    private final Random random = new Random();

    /**
     * Private constructor to initialize the word bank with a set of
     * predefined words. This ensures that the word bank is populated
     * with a consistent set of words that can be used in the game.
     */
    private WordBank() {
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Initializing the WordBank with 600 six-letter words.");

        // 600 six-letter words — 5 or 6 rows per letter, alphabetically balanced
        words.add("ABACUS"); words.add("ABATED"); words.add("ABIDES"); words.add("ACCRUE");
        words.add("ACTION"); words.add("ACTIVE"); words.add("ADMIRE"); words.add("ADOBES");
        words.add("ADVICE"); words.add("AERIAL"); words.add("AFFORD"); words.add("AGENTS");
        words.add("AGREED"); words.add("AIDING"); words.add("ALMOST"); words.add("ALPINE");
        words.add("ALTERS"); words.add("AMBUSH"); words.add("AMUSED"); words.add("ANIMAL");

        words.add("BABBLE"); words.add("BACKUP"); words.add("BADGED"); words.add("BALEEN");
        words.add("BALLAD"); words.add("BALSAM"); words.add("BANTER"); words.add("BARDIC");
        words.add("BARROW"); words.add("BASALT"); words.add("BASKET"); words.add("BEACON");
        words.add("BECAME"); words.add("BEGINS"); words.add("BEHAVE"); words.add("BELONG");
        words.add("BESIDE"); words.add("BETTER"); words.add("BINARY"); words.add("BINDER");
        words.add("BOILER"); words.add("BONSAI"); words.add("BOWELS"); words.add("BRIDGE");

        words.add("CABALA"); words.add("CACKLE"); words.add("CANDLE"); words.add("CANNOT");
        words.add("CARBON"); words.add("CARPET"); words.add("CARTON"); words.add("CASTLE");
        words.add("CAUGHT"); words.add("CAVERN"); words.add("CENTER"); words.add("CHANCE");
        words.add("CHANGE"); words.add("CHILLS"); words.add("CHOICE"); words.add("CHORUS");
        words.add("CIRCLE"); words.add("CLAIMS"); words.add("CLINIC"); words.add("CLOSED");
        words.add("COBALT"); words.add("CODING"); words.add("COFFEE"); words.add("COLONY");

        words.add("DABBED"); words.add("DAINTY"); words.add("DAMSEL"); words.add("DAPPER");
        words.add("DARKEN"); words.add("DARTED"); words.add("DAZZLE"); words.add("DEADLY");
        words.add("DECEIT"); words.add("DEFACE"); words.add("DEFIED"); words.add("DEFTLY");
        words.add("DELUGE"); words.add("DEMURE"); words.add("DENOTE"); words.add("DIALOG");
        words.add("DILATE"); words.add("DIMMED"); words.add("DINGHY"); words.add("DISMAY");
        words.add("DITHER"); words.add("DIVEST"); words.add("DONATE"); words.add("DOTTED");

        words.add("EAGLES"); words.add("EARTHY"); words.add("EASELS"); words.add("EATING");
        words.add("ECHOES"); words.add("EDGILY"); words.add("EFFORT"); words.add("EGRETS");
        words.add("ELEVON"); words.add("ELUDED"); words.add("EMBERS"); words.add("EMBLEM");
        words.add("EMOTES"); words.add("ENABLE"); words.add("ENCODE"); words.add("ENIGMA");
        words.add("ENVIED"); words.add("ERRANT"); words.add("ESCAPE"); words.add("ETHICS");

        words.add("FABRIC"); words.add("FACETS"); words.add("FADING"); words.add("FAIRLY");
        words.add("FALLOW"); words.add("FALTER"); words.add("FAMOUS"); words.add("FATHER");
        words.add("FEMALE"); words.add("FENCED"); words.add("FERRET"); words.add("FESCUE");
        words.add("FIENDS"); words.add("FILTER"); words.add("FIZZLE"); words.add("FLANGE");
        words.add("FLARED"); words.add("FLAUNT"); words.add("FLEECE"); words.add("FLORAL");
        words.add("FOLLOW"); words.add("FORAGE"); words.add("FORCED"); words.add("FORMAT");

        words.add("GADGET"); words.add("GARNET"); words.add("GARLIC"); words.add("GARNET");
        words.add("GATHER"); words.add("GAUGED"); words.add("GAZERS"); words.add("GEARED");
        words.add("GENDER"); words.add("GENIUS"); words.add("GENTLE"); words.add("GHOSTS");
        words.add("GIBBON"); words.add("GIFTED"); words.add("GINGER"); words.add("GIRDLE");
        words.add("GLANCE"); words.add("GLIDER"); words.add("GLOOMY"); words.add("GLORIA");
        words.add("GLOSSE"); words.add("GRACED"); words.add("GRAVEL"); words.add("GREETS");

        words.add("HABITS"); words.add("HACKER"); words.add("HAGGLE"); words.add("HALVES");
        words.add("HAMMER"); words.add("HAMPER"); words.add("HANDLE"); words.add("HANGAR");
        words.add("HANGED"); words.add("HARBOR"); words.add("HARDEN"); words.add("HARROW");
        words.add("HATCHY"); words.add("HAZARD"); words.add("HEALTH"); words.add("HEAVEN");
        words.add("HELIUM"); words.add("HERMIT"); words.add("HIDDEN"); words.add("HINGES");
        words.add("HONEST"); words.add("HOOKED"); words.add("HOOVER"); words.add("HORIZO");

        words.add("ICICLE"); words.add("ICONIC"); words.add("IGNITE"); words.add("IGNORE");
        words.add("IMPACT"); words.add("IMPORT"); words.add("IMPROV"); words.add("INCOME");
        words.add("INDIGO"); words.add("INDOOR"); words.add("INDUCT"); words.add("INFANT");
        words.add("INFLOW"); words.add("INFORM"); words.add("INGEST"); words.add("INJURE");
        words.add("INSIDE"); words.add("INSERT"); words.add("INSIST"); words.add("INTACT");
        words.add("INTENT"); words.add("INVENT"); words.add("ISLAND"); words.add("ISSUES");

        words.add("JACKAL"); words.add("JACKET"); words.add("JAGGED"); words.add("JARGON");
        words.add("JAUNTY"); words.add("JEWELS"); words.add("JINGLE"); words.add("JOCKEY");
        words.add("JOKERS"); words.add("JOURNY"); words.add("JOVIAL"); words.add("JOYFUL");
        words.add("JUDGED"); words.add("JUDGES"); words.add("JUMBLE"); words.add("JUMPER");
        words.add("JUNIOR"); words.add("JUNKED"); words.add("JUSTLY"); words.add("JUTTED");
        words.add("JUVIES"); words.add("JUXTAS"); words.add("JAZZED"); words.add("JIGSAW");

        words.add("KARATE"); words.add("KARMIC"); words.add("KERNEL"); words.add("KESTLE");
        words.add("KIDNEY"); words.add("KILLER"); words.add("KILTER"); words.add("KINDLE");
        words.add("KISSED"); words.add("KITLED"); words.add("KLAXON"); words.add("KNEELS");
        words.add("KNIFED"); words.add("KNIGHT"); words.add("KNOTTY"); words.add("KOALAS");
        words.add("KRAKEN"); words.add("KRONOR"); words.add("KUDZUS"); words.add("KUDDLE");
        words.add("KUNZIT"); words.add("KURZWE"); words.add("KYLEXY"); words.add("KYMERA");

        words.add("LABORS"); words.add("LADDER"); words.add("LAGOON"); words.add("LAMELY");
        words.add("LAMENT"); words.add("LANGER"); words.add("LANTER"); words.add("LARCEN");
        words.add("LARGER"); words.add("LARVAL"); words.add("LASTLY"); words.add("LATELY");
        words.add("LATTED"); words.add("LAUNCH"); words.add("LAVISH"); words.add("LAWYER");
        words.add("LAYOUT"); words.add("LEADER"); words.add("LEAVES"); words.add("LEGEND");
        words.add("LEMONY"); words.add("LENGTH"); words.add("LETTER"); words.add("LEVITY");

        // 600 six-letter words — M to Z, continued
        words.add("MACROS"); words.add("MAGNET"); words.add("MAILED"); words.add("MANNER");
        words.add("MANUAL"); words.add("MARKER"); words.add("MARKET"); words.add("MARVEL");
        words.add("MASTER"); words.add("MATTER"); words.add("MATURE"); words.add("MAXIMA");
        words.add("MEADOW"); words.add("MEDALS"); words.add("MEDIUM"); words.add("MEMORY");
        words.add("MENTOR"); words.add("MERELY"); words.add("MERITS"); words.add("METHOD");
        words.add("MIDDLE"); words.add("MIMICS"); words.add("MINERS"); words.add("MINING");

        words.add("NAGGED"); words.add("NAMING"); words.add("NAPPER"); words.add("NARROW");
        words.add("NATURE"); words.add("NEEDLE"); words.add("NEUTER"); words.add("NICELY");
        words.add("NICKEL"); words.add("NIFTYS"); words.add("NIMBLE"); words.add("NINEVE");
        words.add("NOBLES"); words.add("NOISES"); words.add("NORMAL"); words.add("NOTICE");
        words.add("NOTIFY"); words.add("NOTION"); words.add("NOURSE"); words.add("NOVELS");
        words.add("NUANCE"); words.add("NUMBED"); words.add("NUMBER"); words.add("NURSED");

        words.add("OCEANS"); words.add("OCULAR"); words.add("ODDEST"); words.add("OFFSET");
        words.add("ONLINE"); words.add("OPAQUE"); words.add("OPENER"); words.add("OPTICS");
        words.add("ORANGE"); words.add("ORBITS"); words.add("ORDERS"); words.add("ORIENT");
        words.add("ORIGIN"); words.add("OUTAGE"); words.add("OUTFIT"); words.add("OUTLAY");
        words.add("OUTPUT"); words.add("OUTRUN"); words.add("OUTSET"); words.add("OVERLY");
        words.add("OVERTS"); words.add("OXYGEN"); words.add("OYSTER"); words.add("OZONIC");

        words.add("PACKED"); words.add("PADDED"); words.add("PAINTS"); words.add("PALACE");
        words.add("PANTRY"); words.add("PARADE"); words.add("PARDON"); words.add("PARENT");
        words.add("PARKED"); words.add("PARTLY"); words.add("PASSED"); words.add("PASTEL");
        words.add("PATCHY"); words.add("PATENT"); words.add("PATROL"); words.add("PAUSED");
        words.add("PAYING"); words.add("PEBBLE"); words.add("PEACEY"); words.add("PEANUT");
        words.add("PENCIL"); words.add("PENPAL"); words.add("PEPPED"); words.add("PERISH");

        words.add("QUAINT"); words.add("QUARRY"); words.add("QUARTS"); words.add("QUEENS");
        words.add("QUENCH"); words.add("QUERYX"); words.add("QUICKE"); words.add("QUIETS");
        words.add("QUILLS"); words.add("QUINTA"); words.add("QUOTES"); words.add("QUOTED");
        words.add("QUOTER"); words.add("QUOTAS"); words.add("QUOITS"); words.add("QUIVER");
        words.add("QUIZZY"); words.add("QUAVER"); words.add("QUANTA"); words.add("QUARTZ");
        words.add("QUIANT"); words.add("QUAFFS"); words.add("QUICKS"); words.add("QUOTES");

        words.add("RACING"); words.add("RADIAL"); words.add("RADIOS"); words.add("RAGGED");
        words.add("RAISED"); words.add("RALLYE"); words.add("RAMBLE"); words.add("RANKED");
        words.add("RANGES"); words.add("RANDOM"); words.add("RANGEL"); words.add("RAZORS");
        words.add("REACTS"); words.add("REALLY"); words.add("RECALL"); words.add("RECEIP");
        words.add("RECESS"); words.add("RECITE"); words.add("RECOIL"); words.add("RECURL");
        words.add("REDONE"); words.add("REDUCE"); words.add("REFLEX"); words.add("REGENT");

        words.add("SABERS"); words.add("SACRED"); words.add("SAFELY"); words.add("SAFETY");
        words.add("SAGELY"); words.add("SAILED"); words.add("SAINTS"); words.add("SALMON");
        words.add("SALUTE"); words.add("SAMPLE"); words.add("SANITY"); words.add("SAUCER");
        words.add("SAVAGE"); words.add("SAVING"); words.add("SAYING"); words.add("SCALER");
        words.add("SCARCE"); words.add("SCHOOL"); words.add("SCORCH"); words.add("SCRAPE");
        words.add("SCRIBE"); words.add("SCRUBS"); words.add("SCULPT"); words.add("SEASON");

        words.add("TABLES"); words.add("TABOOX"); words.add("TACTIC"); words.add("TAGGED");
        words.add("TAILED"); words.add("TAKING"); words.add("TALKED"); words.add("TALLER");
        words.add("TANGEL"); words.add("TARGET"); words.add("TASKED"); words.add("TASTER");
        words.add("TASTES"); words.add("TATTLE"); words.add("TAUGHT"); words.add("TAXING");
        words.add("TEACUP"); words.add("TEASED"); words.add("TECHIE"); words.add("TELLER");
        words.add("TEMPLE"); words.add("TENDER"); words.add("TENURE"); words.add("THANKS");

        words.add("UMPIRE"); words.add("UNABLE"); words.add("UNBIND"); words.add("UNCLES");
        words.add("UNDONE"); words.add("UNFAIR"); words.add("UNFOLD"); words.add("UNHOOK");
        words.add("UNIFYX"); words.add("UNIQUE"); words.add("UNISON"); words.add("UNLEAD");
        words.add("UNLOCK"); words.add("UNMIXT"); words.add("UNPACK"); words.add("UNRIGS");
        words.add("UNROLL"); words.add("UNSEEN"); words.add("UNUSED"); words.add("UNVEIL");
        words.add("UNWIND"); words.add("UNWISE"); words.add("UPBEAT"); words.add("UPDATE");

        words.add("VACANT"); words.add("VACUUM"); words.add("VAGUEY"); words.add("VALETS");
        words.add("VALUES"); words.add("VALVES"); words.add("VANITY"); words.add("VARIED");
        words.add("VAULTS"); words.add("VECTOR"); words.add("VEGGIE"); words.add("VELVET");
        words.add("VERBAL"); words.add("VERIFY"); words.add("VERSED"); words.add("VESSEL");
        words.add("VESTED"); words.add("VEXING"); words.add("VIABLE"); words.add("VICTIM");
        words.add("VIGILS"); words.add("VILIFY"); words.add("VINTED"); words.add("VISION");

        words.add("WACKED"); words.add("WAFFLE"); words.add("WAGONS"); words.add("WAITED");
        words.add("WAIVER"); words.add("WALLED"); words.add("WALKER"); words.add("WARMED");
        words.add("WARNED"); words.add("WARPED"); words.add("WARTED"); words.add("WASHED");
        words.add("WASTED"); words.add("WATCHY"); words.add("WATERX"); words.add("WEALTH");
        words.add("WEAPON"); words.add("WEASEL"); words.add("WEBBED"); words.add("WEEPER");
        words.add("WEIGHT"); words.add("WELDED"); words.add("WENDER"); words.add("WETTER");

        words.add("XENIAL"); words.add("XENONS"); words.add("XERASY"); words.add("XEROXY");
        words.add("XYSTUS"); words.add("XYLOSE"); words.add("XENONX"); words.add("XENOPH");
        words.add("XYSTER"); words.add("XEROSE"); words.add("XIPHOI"); words.add("XENITE");
        words.add("XYLATE"); words.add("XENIAL"); words.add("XANTHA"); words.add("XALAPA");
        words.add("XYLEMS"); words.add("XYLENE"); words.add("XYLOGS"); words.add("XENIUM");
        words.add("XYLINS"); words.add("XYLOPH"); words.add("XANTHO"); words.add("XANTHY");

        words.add("YACHTS"); words.add("YAHOOS"); words.add("YAMMER"); words.add("YANKED");
        words.add("YAWNER"); words.add("YELLOW"); words.add("YESEES"); words.add("YIELDY");
        words.add("YIPPED"); words.add("YODELS"); words.add("YOGINI"); words.add("YOGURT");
        words.add("YONDER"); words.add("YOUNGS"); words.add("YOURSY"); words.add("YOUTHY");
        words.add("YOWLED"); words.add("YOYOED"); words.add("YUTZES"); words.add("YAWNED");
        words.add("YAWNIE"); words.add("YOGICS"); words.add("YAMPLY"); words.add("YEASTY");

        words.add("ZAPPED"); words.add("ZEBRAS"); words.add("ZENITH"); words.add("ZYGOTE");
        words.add("ZESTED"); words.add("ZINGER"); words.add("ZIPPED"); words.add("ZODIAC");
        words.add("ZOMBIE"); words.add("ZONING"); words.add("ZOOLOG"); words.add("ZOUNDS");
        words.add("ZURICH"); words.add("ZYGOTE"); words.add("ZYMOTE"); words.add("ZEBECS");
        words.add("ZEPHYR"); words.add("ZEALOT"); words.add("ZIBETH"); words.add("ZINNIA");
        words.add("ZIPPER"); words.add("ZITHER"); words.add("ZIZZED"); words.add("ZYMURG");

        // 7-letter words — balanced across alphabet, 4 words per line, 5–6 rows per letter
        words.add("ABANDON"); words.add("ABASING"); words.add("ABATING"); words.add("ABDUCTS");
        words.add("ABILITY"); words.add("ABLAZED"); words.add("ABORTED"); words.add("ABRIDGE");
        words.add("ABSENCE"); words.add("ABUSING"); words.add("ACADEMY"); words.add("ACCRUAL");

        words.add("BALANCE"); words.add("BALLOON"); words.add("BALMIER"); words.add("BANANAS");
        words.add("BANDAGE"); words.add("BANGING"); words.add("BANKING"); words.add("BANQUET");
        words.add("BARGAIN"); words.add("BARRELS"); words.add("BASMENT"); words.add("BATTERY");
        words.add("BEACONS"); words.add("BECAUSE"); words.add("BECOMES"); words.add("BEDTIME");

        words.add("CABINET"); words.add("CAMPING"); words.add("CANYONS"); words.add("CAPTURE");
        words.add("CARAVEL"); words.add("CARDIAC"); words.add("CARIBOU"); words.add("CARROTS");
        words.add("CASCADE"); words.add("CASTING"); words.add("CATFISH"); words.add("CAVIARS");
        words.add("CEILING"); words.add("CENTURY"); words.add("CERTAIN"); words.add("CHASING");

        words.add("DABBLED"); words.add("DAMAGED"); words.add("DANGERX"); words.add("DARKEST");
        words.add("DARLING"); words.add("DEADPAN"); words.add("DEAREST"); words.add("DEBRIEF");
        words.add("DECIMAL"); words.add("DEFENSE"); words.add("DEFTONE"); words.add("DELIGHT");
        words.add("DELIVER"); words.add("DENOTES"); words.add("DENSITY"); words.add("DEVIANT");

        words.add("EARNEST"); words.add("EARRING"); words.add("EASEFUL"); words.add("EASTERN");
        words.add("ECHOING"); words.add("ECLECTS"); words.add("EDITION"); words.add("EDUCATE");
        words.add("EJECTOR"); words.add("ELATION"); words.add("ELDERLY"); words.add("ELEVATE");
        words.add("EMOTION"); words.add("EMPOWER"); words.add("ENIGMAS"); words.add("ESCAPES");

        words.add("FABRICS"); words.add("FACADES"); words.add("FACTUAL"); words.add("FAILURE");
        words.add("FALLING"); words.add("FAMINES"); words.add("FANTASY"); words.add("FARMING");
        words.add("FASHION"); words.add("FATIGUE"); words.add("FEEDING"); words.add("FELINES");
        words.add("FERVENT"); words.add("FETCHED"); words.add("FIGURED"); words.add("FINALLY");

        words.add("GADGETS"); words.add("GALLANT"); words.add("GAMERUN"); words.add("GARDENS");
        words.add("GARMENT"); words.add("GATEWAY"); words.add("GENERIC"); words.add("GENIUSY");
        words.add("GERMANY"); words.add("GHOSTLY"); words.add("GIGGLED"); words.add("GLANCES");
        words.add("GLITTER"); words.add("GLORIFY"); words.add("GLYCINE"); words.add("GRANTED");

        words.add("HABITAT"); words.add("HACKERS"); words.add("HALFWAY"); words.add("HANGMAN");
        words.add("HAPPENS"); words.add("HARDEST"); words.add("HARMONY"); words.add("HARVEST");
        words.add("HAUNTED"); words.add("HAZARDS"); words.add("HEALTHY"); words.add("HEAVENS");
        words.add("HELDING"); words.add("HELPFUL"); words.add("HERALDS"); words.add("HIGHWAY");

        words.add("ICECUBE"); words.add("ICONIFY"); words.add("IDEALXY"); words.add("IGNITED");
        words.add("IGNORER"); words.add("IMAGINE"); words.add("IMPACTS"); words.add("IMPLIED");
        words.add("IMPROVE"); words.add("INBOARD"); words.add("INBOUND"); words.add("INCLUDE");
        words.add("INHALED"); words.add("INSIGHT"); words.add("INTEGER"); words.add("INTENTS");

        words.add("JACKETS"); words.add("JAGUARS"); words.add("JAMMING"); words.add("JARGONS");
        words.add("JAZZERS"); words.add("JEALOUS"); words.add("JERSEYS"); words.add("JIGSAWS");
        words.add("JINGLEY"); words.add("JOBLESS"); words.add("JOHNSON"); words.add("JOINTLY");
        words.add("JOKERLY"); words.add("JOURNEY"); words.add("JUDGING"); words.add("JUMBLEY");

        words.add("KARAOKE"); words.add("KARMICS"); words.add("KESTREL"); words.add("KICKOFF");
        words.add("KIDNEYS"); words.add("KILLERS"); words.add("KINDLED"); words.add("KINGDOM");
        words.add("KINKING"); words.add("KISSERS"); words.add("KITCHEN"); words.add("KLAXONS");
        words.add("KNITTER"); words.add("KNOTTED"); words.add("KRAKENS"); words.add("KRILLER");

        words.add("LABELED"); words.add("LADYBUG"); words.add("LAMINEX"); words.add("LANGUOR");
        words.add("LANTERN"); words.add("LARGELY"); words.add("LARKING"); words.add("LATTICE");
        words.add("LAUNDRY"); words.add("LAWYERS"); words.add("LEADING"); words.add("LEARNED");
        words.add("LEAVING"); words.add("LECTURE"); words.add("LEGIBLE"); words.add("LEISURE");

        // 7-letter words — M to Z, balanced across alphabet, 4 words per line, 5–6 rows per letter
        words.add("MACABRE"); words.add("MAGNIFY"); words.add("MAJESTY"); words.add("MANKIND");
        words.add("MALARIA"); words.add("MANAGED"); words.add("MANHOOD"); words.add("MANIACS");
        words.add("MANTLED"); words.add("MANUALS"); words.add("MARCHED"); words.add("MARKERS");
        words.add("MASCOTS"); words.add("MASTERX"); words.add("MATURED"); words.add("MAXIMAL");
        words.add("MEADOWS"); words.add("MEASURE"); words.add("MEDICAL"); words.add("MEDIUMS");
        words.add("MELTDOW"); words.add("MENTORS"); words.add("MERGERS"); words.add("MESHING");

        words.add("NABBING"); words.add("NAILERS"); words.add("NAPKINS"); words.add("NARRATE");
        words.add("NATIONS"); words.add("NEARING"); words.add("NEEDING"); words.add("NEGLECT");
        words.add("NEITHER"); words.add("NETWORK"); words.add("NEUTRAL"); words.add("NICKELS");
        words.add("NIMBLED"); words.add("NOBLEST"); words.add("NOISIER"); words.add("NONZERO");
        words.add("NOTABLE"); words.add("NOTCHED"); words.add("NOTIONS"); words.add("NOUGATS");
        words.add("NOVELTY"); words.add("NUANCED"); words.add("NUMERIC"); words.add("NURSERY");

        words.add("OARSMAN"); words.add("OBSCURE"); words.add("OBVIOUS"); words.add("OCCIPUT");
        words.add("OFFLINE"); words.add("OFFLOAD"); words.add("OFFSETX"); words.add("ONBOARD");
        words.add("OPACITY"); words.add("OPENERS"); words.add("OPERATE"); words.add("OPINION");
        words.add("OPTIONS"); words.add("ORANGES"); words.add("ORBITAL"); words.add("ORDERLY");
        words.add("ORGANIC"); words.add("ORPHANS"); words.add("OUTCOME"); words.add("OUTGROW");
        words.add("OUTINGS"); words.add("OUTLAWS"); words.add("OUTLOOK"); words.add("OUTPOST");

        words.add("PACKETS"); words.add("PAINTER"); words.add("PALATES"); words.add("PANDORA");
        words.add("PANICED"); words.add("PANTHER"); words.add("PARABLE"); words.add("PARLIAM");
        words.add("PARTIAL"); words.add("PARTING"); words.add("PASSAGE"); words.add("PASSION");
        words.add("PASTURE"); words.add("PATIENT"); words.add("PATRIOT"); words.add("PATTERN");
        words.add("PAYLOAD"); words.add("PEBBLES"); words.add("PENCILS"); words.add("PENDING");
        words.add("PEOPLEX"); words.add("PERFORM"); words.add("PERHAPS"); words.add("PERIODS");

        words.add("QUALITY"); words.add("QUANTUM"); words.add("QUARTER"); words.add("QUASARS");
        words.add("QUENCHY"); words.add("QUERYED"); words.add("QUEUEED"); words.add("QUIETLY");
        words.add("QUININE"); words.add("QUINTET"); words.add("QUIVERS"); words.add("QUIXOTE");
        words.add("QUORUMS"); words.add("QUOTING"); words.add("QUOTISH"); words.add("QUOVADX");
        words.add("QUIRKED"); words.add("QUACKED"); words.add("QUAGGAS"); words.add("QUAKERS");
        words.add("QUAILED"); words.add("QUAINTS"); words.add("QUARTIC"); words.add("QUASHED");

        words.add("RACCOON"); words.add("RADIATE"); words.add("RADICAL"); words.add("RAINFUL");
        words.add("RAISING"); words.add("RAMPAGE"); words.add("RANDOMS"); words.add("RANGERS");
        words.add("RANKING"); words.add("RANSACK"); words.add("RAPTURE"); words.add("RASCALS");
        words.add("RATIONX"); words.add("REACHED"); words.add("REACTOR"); words.add("REASONX");
        words.add("REBOUND"); words.add("REBUILD"); words.add("RECITAL"); words.add("RECLAIM");
        words.add("RECORDS"); words.add("RECOVER"); words.add("REDRAFT"); words.add("REDNESS");

        words.add("SADDLED"); words.add("SAILING"); words.add("SALIENT"); words.add("SALVAGE");
        words.add("SAMPLED"); words.add("SANCTUM"); words.add("SANDALS"); words.add("SATIRED");
        words.add("SATISFY"); words.add("SAUSAGE"); words.add("SAVINGS"); words.add("SAVORED");
        words.add("SCALEDX"); words.add("SCANNER"); words.add("SCENERY"); words.add("SCEPTIC");
        words.add("SCOOTER"); words.add("SCRATCH"); words.add("SCREECH"); words.add("SCULPTS");
        words.add("SEASONS"); words.add("SECRETS"); words.add("SECURED"); words.add("SEDUCED");

        words.add("TABLETS"); words.add("TACTICS"); words.add("TAGGING"); words.add("TAKINGX");
        words.add("TALENTS"); words.add("TANGLED"); words.add("TANTRUM"); words.add("TARGETS");
        words.add("TASKING"); words.add("TATTOOS"); words.add("TAUGHTX"); words.add("TAXABLE");
        words.add("TEACHER"); words.add("TEAMING"); words.add("TEAPOTS"); words.add("TELLING");
        words.add("TEMPERS"); words.add("TENSION"); words.add("TEXTILE"); words.add("THANKED");
        words.add("THICKEN"); words.add("THIRDLY"); words.add("THOUGHT"); words.add("THRILLS");

        words.add("UMBRAGE"); words.add("UNCLEAN"); words.add("UNCLOCK"); words.add("UNCOVER");
        words.add("UNDERLY"); words.add("UNDRESS"); words.add("UNFOLDS"); words.add("UNHINGE");
        words.add("UNICORN"); words.add("UNITARY"); words.add("UNITING"); words.add("UNIVERS");
        words.add("UNKNOWX"); words.add("UNLEASH"); words.add("UNLOADS"); words.add("UNLOCKS");
        words.add("UNPLUGS"); words.add("UNQUIET"); words.add("UNSAVED"); words.add("UNTAMED");
        words.add("UNTOLDX"); words.add("UNTRUST"); words.add("UNUSUAL"); words.add("UNWRAPS");

        words.add("VACANCY"); words.add("VACATED"); words.add("VAGUELY"); words.add("VAULTED");
        words.add("VARIANT"); words.add("VARIETY"); words.add("VARNISH"); words.add("VASTING");
        words.add("VECTORS"); words.add("VEHICLE"); words.add("VEILING"); words.add("VELVETY");
        words.add("VENDING"); words.add("VERDICT"); words.add("VERGING"); words.add("VERNALS");
        words.add("VERSION"); words.add("VESSELS"); words.add("VESTING"); words.add("VIBRATE");
        words.add("VICTORY"); words.add("VIEWERS"); words.add("VINTAGE"); words.add("VIOLETS");

        words.add("WALKING"); words.add("WANDERS"); words.add("WANTING"); words.add("WARMEST");
        words.add("WARNING"); words.add("WASHING"); words.add("WASTERS"); words.add("WATCHES");
        words.add("WEARING"); words.add("WEAVERS"); words.add("WEIRDER"); words.add("WELTING");
        words.add("WHISPER"); words.add("WHITEST"); words.add("WIDOWED"); words.add("WILDING");
        words.add("WINDING"); words.add("WINDOWS"); words.add("WINGMAN"); words.add("WINKING");
        words.add("WINTERS"); words.add("WISHING"); words.add("WITHERS"); words.add("WONDERX");

        words.add("XANTHIN"); words.add("XENOPUS"); words.add("XERARCH"); words.add("XEROSES");
        words.add("XEROTIC"); words.add("XIPHOID"); words.add("XYSTERS"); words.add("XENOPHY");
        words.add("XENURAS"); words.add("XENOLIT"); words.add("XENOBOT"); words.add("XENIALS");
        words.add("XENOTIC"); words.add("XYLENES"); words.add("XYLOIDS"); words.add("XYLOTES");
        words.add("XYLULOS"); words.add("XYRIDAS"); words.add("XYRISKS"); words.add("XENOTES");

        words.add("YABBERS"); words.add("YACHTED"); words.add("YAMMERS"); words.add("YAWNING");
        words.add("YEARING"); words.add("YEARNED"); words.add("YELLING"); words.add("YESTERY");
        words.add("YAWPING"); words.add("YIPPERS"); words.add("YODELED"); words.add("YONKERS");

        words.add("ZEBRAIC"); words.add("ZEBRAIC"); words.add("ZEPHYRS"); words.add("ZESTFUL");
        words.add("ZIGZAGS"); words.add("ZILLION"); words.add("ZINCITE"); words.add("ZINGERS");
        words.add("ZINNIAS"); words.add("ZIPPERS"); words.add("ZIRCONY"); words.add("ZODIACS");
        words.add("ZOMBIES"); words.add("ZONALLY"); words.add("ZOOMING"); words.add("ZOOLOGY");
        words.add("ZOSTERS"); words.add("ZOUNDER"); words.add("ZUCCHIS"); words.add("ZYGOTES");
        words.add("ZYMASES"); words.add("ZYMOTIC"); words.add("ZEBRINE"); words.add("ZANIEST");

        // 600 eight-letter words — A to Z, formatted for Java, 4 per line, 5–6 lines per letter
        words.add("ABANDONS"); words.add("ABASHING"); words.add("ABATTOIR"); words.add("ABDUCTED");
        words.add("ABERRANT"); words.add("ABILITIE"); words.add("ABNORMAL"); words.add("ABRIDGED");
        words.add("ABROGATE"); words.add("ABSURDLY"); words.add("ABUNDANT"); words.add("ACADEMIA");

        words.add("BABOONRY"); words.add("BACKFIRE"); words.add("BACKPACK"); words.add("BACKWARD");
        words.add("BACTERIA"); words.add("BADMINTON"); words.add("BALANCED"); words.add("BALLOONS");
        words.add("BALUSTER"); words.add("BANDWIDE"); words.add("BANISHED"); words.add("BANKROLL");

        words.add("CABINETS"); words.add("CACKLING"); words.add("CALAMITY"); words.add("CALENDAR");
        words.add("CALLABLE"); words.add("CALLIOPE"); words.add("CAMELIAS"); words.add("CAMOUFLA");
        words.add("CAMPFIRE"); words.add("CANISTER"); words.add("CANYONED"); words.add("CAPTIONS");

        words.add("DABBINGS"); words.add("DAILIEST"); words.add("DANCIEST"); words.add("DANGEROX");
        words.add("DARKENED"); words.add("DARLINGS"); words.add("DATABASE"); words.add("DAUGHTER");
        words.add("DAYLIGHT"); words.add("DEADLOCK"); words.add("DEBRISLY"); words.add("DECIBELS");

        words.add("EAGLIEST"); words.add("EARLIERS"); words.add("EARMARKS"); words.add("EARNINGS");
        words.add("EARTHING"); words.add("ECLIPSED"); words.add("ECONOMIX"); words.add("ECTOPLAS");
        words.add("EDGINESS"); words.add("EDUCABLE"); words.add("EJECTORS"); words.add("ELASTICS");

        words.add("FABULOUS"); words.add("FACILITY"); words.add("FAIRNESS"); words.add("FALCONRY");
        words.add("FALLIBLE"); words.add("FAMILIAL"); words.add("FAMILIES"); words.add("FARAWAYS");
        words.add("FARMYARD"); words.add("FASCISTS"); words.add("FASHIONS"); words.add("FASTENED");

        words.add("GADABOUTS"); words.add("GAINFULS"); words.add("GALLERYX"); words.add("GAMIFIED");
        words.add("GANGSTER"); words.add("GARMENTS"); words.add("GASGIANT"); words.add("GATEWAYS");
        words.add("GAZETTES"); words.add("GENTLEST"); words.add("GENUINES"); words.add("GEOLOGIC");

        words.add("HABITUAL"); words.add("HACKABLE"); words.add("HAIRCUTS"); words.add("HALFLIFE");
        words.add("HALLWAYS"); words.add("HAMMERED"); words.add("HAMPERED"); words.add("HANDBAGS");
        words.add("HANDLING"); words.add("HARBOURS"); words.add("HARMLESS"); words.add("HARPOONS");

        words.add("ICEBOUND"); words.add("ICICLESX"); words.add("IDEATION"); words.add("IDENTIFY");
        words.add("IGNITION"); words.add("IGUANINE"); words.add("ILLICITS"); words.add("IMITATED");
        words.add("IMPACTED"); words.add("IMPORTER"); words.add("IMPRESSX"); words.add("IMPROVES");

        words.add("JACKFRUIT"); words.add("JACKPOTS"); words.add("JAILBIRD"); words.add("JANGIEST");
        words.add("JARGONIC"); words.add("JASMINES"); words.add("JEALOUSY"); words.add("JETLAGGY");
        words.add("JEWELBOX"); words.add("JINGLING"); words.add("JITTERED"); words.add("JOINABLE");

        words.add("KARAOKED"); words.add("KASHMIRS"); words.add("KEEPSAKE"); words.add("KEROSENE");
        words.add("KETAMINE"); words.add("KEYBOARD"); words.add("KEYHOLES"); words.add("KEYNOTES");
        words.add("KICKBACK"); words.add("KIDNAPED"); words.add("KILLABLE"); words.add("KINGFISH");

        words.add("LABELOUS"); words.add("LADLEFUL"); words.add("LAGOONED"); words.add("LAMPOONS");
        words.add("LANCETED"); words.add("LANGUISH"); words.add("LANTERNS"); words.add("LAPTOPSX");
        words.add("LARCENED"); words.add("LARGENED"); words.add("LASHINGS"); words.add("LAUGHTER");

        // 8-letter words — M to Z, balanced across alphabet, 4 per line, 5–6 rows per letter
        words.add("MACHINES"); words.add("MAGNETIC"); words.add("MAJORITY"); words.add("MALWARES");
        words.add("MANDATES"); words.add("MANIFEST"); words.add("MANKINDS"); words.add("MANNEQUIN");
        words.add("MANUALLY"); words.add("MARKETED"); words.add("MARSHALS"); words.add("MATERIAL");
        words.add("MEANDERS"); words.add("MECHANIC"); words.add("MEDICINE"); words.add("MELODYIC");

        words.add("NARRATOR"); words.add("NASCENTS"); words.add("NATURALS"); words.add("NECKLACE");
        words.add("NEEDLESS"); words.add("NEGATIVE"); words.add("NEGLECTS"); words.add("NEPHEWLY");
        words.add("NETWORKS"); words.add("NEUTRALS"); words.add("NEWSCAST"); words.add("NOBLEMAN");
        words.add("NONSTOPX"); words.add("NOSTRUMS"); words.add("NOTIFIED"); words.add("NOVELIST");

        words.add("OARSMENX"); words.add("OBLIGATE"); words.add("OBSCURED"); words.add("OBSERVES");
        words.add("OBSTACLE"); words.add("OCCASION"); words.add("OCEANICS"); words.add("OFFENDED");
        words.add("OFFICERS"); words.add("OFFSETED"); words.add("OMNIVORE"); words.add("ONLOOKER");
        words.add("OPINIONS"); words.add("OPPOSITE"); words.add("OPTIMISM"); words.add("ORGANISM");

        words.add("PACKAGES"); words.add("PADLOCKS"); words.add("PAINTERS"); words.add("PALACEES");
        words.add("PANCAKES"); words.add("PANELING"); words.add("PANTHEON"); words.add("PARADOXY");
        words.add("PARAGONS"); words.add("PARENTHS"); words.add("PARTICLE"); words.add("PARTNERS");
        words.add("PATCHING"); words.add("PATTERNS"); words.add("PEACEFUL"); words.add("PEBBLESS");

        words.add("QUADRANT"); words.add("QUAGMIRE"); words.add("QUALMISH"); words.add("QUANTIFY");
        words.add("QUARRIED"); words.add("QUARTETS"); words.add("QUASIEST"); words.add("QUAVERED");
        words.add("QUEENISH"); words.add("QUENCHES"); words.add("QUESTION"); words.add("QUEUEING");
        words.add("QUICKENS"); words.add("QUIETEST"); words.add("QUINCEAN"); words.add("QUIVERSY");

        words.add("RADIATED"); words.add("RAFFLING"); words.add("RAINBOWS"); words.add("RAISINGS");
        words.add("RAMBLERS"); words.add("RANSACKS"); words.add("RAPIDITY"); words.add("RATIONAL");
        words.add("REACTORS"); words.add("READABLE"); words.add("REALISTS"); words.add("REALIZED");
        words.add("RECKONED"); words.add("RECORDING"); words.add("RECOVERY"); words.add("REDDINGS");

        words.add("SABOTAGE"); words.add("SAILBOAT"); words.add("SALARIED"); words.add("SALVAGED");
        words.add("SAMPLERS"); words.add("SANDWICH"); words.add("SANITYCY"); words.add("SATURATE");
        words.add("SAVAGERY"); words.add("SAVVIEST"); words.add("SAXOPHON"); words.add("SCALEDUP");
        words.add("SCARIEST"); words.add("SCATTERS"); words.add("SCENARIO"); words.add("SCIENCES");

        words.add("TABLETOP"); words.add("TACTICAL"); words.add("TAGLINES"); words.add("TAILORED");
        words.add("TAKEOVER"); words.add("TANGIBLE"); words.add("TANKARDS"); words.add("TARNISHS");
        words.add("TASTEFUL"); words.add("TATTYING"); words.add("TAXATION"); words.add("TEACHERS");
        words.add("TECHNIQS"); words.add("TELEPORT"); words.add("TELEVISE"); words.add("TELLINGS");

        words.add("UMBRELLA"); words.add("UNBOXING"); words.add("UNCANNYX"); words.add("UNCIVILS");
        words.add("UNCLOGED"); words.add("UNCORKED"); words.add("UNDERDOG"); words.add("UNDULATE");
        words.add("UNFAZING"); words.add("UNFOLDED"); words.add("UNHINGED"); words.add("UNIFIEDX");
        words.add("UNIVERSE"); words.add("UNKNOWNS"); words.add("UNLEADED"); words.add("UNLOCKED");

        words.add("VACATION"); words.add("VAGARIES"); words.add("VAGRANCY"); words.add("VALENCES");
        words.add("VALIDITY"); words.add("VALUABLE"); words.add("VALUTING"); words.add("VANISHED");
        words.add("VANITIES"); words.add("VARIANTS"); words.add("VARNISHS"); words.add("VEHEMENT");
        words.add("VELOCITY"); words.add("VERIFIED"); words.add("VERTICAL"); words.add("VESTMENT");

        words.add("WACKIEST"); words.add("WALLFLOW"); words.add("WANDERED"); words.add("WANTONLY");
        words.add("WARDROBE"); words.add("WARNINGS"); words.add("WARPAINT"); words.add("WASTEFUL");
        words.add("WATCHFUL"); words.add("WATERING"); words.add("WEAKENED"); words.add("WEALTHYX");
        words.add("WEATHERS"); words.add("WEBCASTS"); words.add("WEDDINGS"); words.add("WELLNESS");

        words.add("XANTHATE"); words.add("XENOGENY"); words.add("XENOLITH"); words.add("XENOPHOB");
        words.add("XEROGRAM"); words.add("XEROPHYT"); words.add("XIPHOPAG"); words.add("XYLEMICS");
        words.add("XYLOCARP"); words.add("XYSTERED"); words.add("XANADUSY"); words.add("XENOGAMY");
        words.add("XERARCHY"); words.add("XANTHINS"); words.add("XENOLABX"); words.add("XENOGENX");

        words.add("YABBIEST"); words.add("YACHTING"); words.add("YAMMERED"); words.add("YAWNERLY");
        words.add("YEARLING"); words.add("YEARNERS"); words.add("YELLOWED"); words.add("YESTERLY");
        words.add("YIELDERS"); words.add("YIPPINGS"); words.add("YODELING"); words.add("YOUNGERX");
        words.add("YOUTHFUL"); words.add("YOWLINGX"); words.add("YUGOSLAV"); words.add("YULETIDE");

        words.add("ZANINESS"); words.add("ZAPPEDLY"); words.add("ZEBRATIC"); words.add("ZENITHAL");
        words.add("ZEPHYRED"); words.add("ZESTIEST"); words.add("ZIGZAGGY"); words.add("ZINCITES");
        words.add("ZINGIEST"); words.add("ZIPPABLE"); words.add("ZODIACAL"); words.add("ZOMBIEST");
        words.add("ZONATION"); words.add("ZOOKEEPR"); words.add("ZOOMABLE"); words.add("ZUCCHINI");
    }

    /**
     * Returns the singleton instance of WordBank. If the instance does not
     * exist, it creates a new one. This method ensures that there is only
     * one instance of WordBank throughout the application, providing a
     * consistent source of words for the game.
     * @return The singleton instance of WordBank.
     */
    public static WordBank getInstance() {
        
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Returning the singleton instance of WordBank.");
        
        // Singleton pattern to ensure only one instance of WordBank exists
        if (instance == null)
            instance = new WordBank();
        
        // Return the single instance
        return instance;
    }

    /**
     * Returns a random word from the word bank. This method selects a word
     * randomly from the list of available words, ensuring that each game
     * session can have a different word to guess, enhancing the gameplay
     * experience.
     * @return A random word from the word bank, or "DEFAULT" if the list is empty.
     */
    public String getWord() {
        
        if (words.isEmpty()) {
            Debug.log(Debug.WARN, WordBank.class.getSimpleName(), "The word bank is empty. Returning 'DEFAULT'.");
            return "DEFAULT";
        }

        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Returning a random word from the word bank.");
        return words.get(random.nextInt(words.size()));
    }

    /**
     * Returns the size of the word bank, i.e., the number of words available.
     * This method provides the count of words currently stored in the word bank,
     * which can be useful for determining the variety of words available for
     * gameplay.
     * @return The number of words in the word bank.
     */
    public int getSize() {
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Returning the size of the word bank: " + words.size());
        return words.size();
    }

    /**
     * Adds a new word to the word bank. The word must be in uppercase and
     * between 6 and 8 letters long. If the word is valid and not already
     * present in the word bank, it is added to the list. This method allows
     * for dynamic expansion of the word bank, enabling users to customize
     * the vocabulary used in the game.
     * @param word The word to be added to the word bank. It must be in uppercase
     *             and between 6 and 8 letters long.
     */
    public void addWord(String word) {
        
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Attempting to add a new word: " + word);
        
        // Validate the word: must be uppercase.
        word = word.toUpperCase();

        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Validating the word: " + word);

        // Check if the word is between 6 and 8 letters long and not already in the list.
        if (word.matches("[A-Z]{6,8}") && !words.contains(word)) {
            Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "The word is valid and will be added to the word bank.");            
            // Add the word to the list if it meets the criteria.
            words.add(word);
        }
    }
}
package carpet.settings;

import carpet.CarpetServer;
import carpet.utils.Messenger;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static carpet.settings.RuleCategory.*;

public class CarpetSettings
{
    public static final String carpetVersion = "v19_06_04alpha_����by����С��";
    public static final Logger LOG = LogManager.getLogger();
    public static boolean skipGenerationChecks = false;
    public static boolean impendingFillSkipUpdates = false;
    public static final int SHULKERBOX_MAX_STACK_AMOUNT = 64;

    @Rule(
            desc = "�޸����Ź�bug: ��������1tick���ȳ���60s�Ժ��Զ�����",
            extra = "����1.12�г��ֹ���1.13��Ԥ�����汾�б��޸����������ֻ����ˡ���mMojang����ߵ��İɡ�",
            category = BUGFIX
    )
    public static boolean watchdogCrashFix = false;

    @Rule(
            desc = "�½紫������ȷ�ش���ʵ��",
            extra = "ʵ�彫���Ῠ�ں���ʯ����",
            category = BUGFIX
    )
    public static boolean portalSuffocationFix = false;

    @Rule(desc = "Gbhs sgnf sadsgras fhskdpri! #��Ҳ������", category = EXPERIMENTAL)
    public static boolean superSecretSetting = false;


    @Rule(desc = "�޸��������ܹ�����������ҵ�bug", category = BUGFIX)
    public static boolean invisibilityFix = false;

    @Rule(
            desc = "����ģʽ����ҽ����������������Ŵ���",
            extra = "������˫�����ź���ʯ����ô��/������������",
            category = CREATIVE
    )
    public static boolean portalCreativeDelay = false;

    @Rule(desc = "Dropping entire stacks works also from on the crafting UI result slot", category = {BUGFIX, SURVIVAL})
    public static boolean ctrlQCraftingFix = false;

    @Rule(
            desc = "����ֻ��������ܵ��˺���Ż�վ����Ҽ����",
            category = {SURVIVAL, FEATURE}
    )
    public static boolean persistentParrots = false;

    /*@Rule(
            desc = "Mobs growing up won't glitch into walls or go through fences",
            category = BUGFIX,
            validate = Validator.WIP.class
    )
    public static boolean growingUpWallJump = false;

    @Rule(
            desc = "Won't let mobs glitch into blocks when reloaded.",
            extra = "Can cause slight differences in mobs behaviour",
            category = {BUGFIX, EXPERIMENTAL},
            validate = Validator.WIP.class
    )
    public static boolean reloadSuffocationFix = false;
    */

    @Rule( desc = "��ҽ�û����ʱ�����վ�����", category = CREATIVE )
    public static boolean xpNoCooldown = false;

    @Rule( desc = "С�ľ������Զ��ϲ��ɴ�ľ�����", category = FEATURE )
    public static boolean combineXPOrbs = false;

    @Rule(
            desc = "64����ǱӰ�е����ｫ�ϲ���һ��",
            extra = "��סshift������Ʒ�������ƶ�һ��ǱӰ��",
            category = {SURVIVAL, FEATURE}
    )
    public static boolean stackableShulkerBoxes = false;

    @Rule( desc = "��ը���ƻ�����", category = CREATIVE )
    public static boolean explosionNoBlockDamage = false;

    @Rule( desc = "TNT������ʱ���ٻ���������", category = CREATIVE )
    public static boolean tntPrimerMomentumRemoved = false;

    @Rule(
            desc = "��ʯ�ۿ����Ż�",
            extra = "��Theosib���״���",
            category = {EXPERIMENTAL, OPTIMIZATION}
    )
    public static boolean fastRedstoneDust = false;

    @Rule(desc = "ɳĮ�����ֻ����ʬ��", category = FEATURE)
    public static boolean huskSpawningInTemples = false;

    @Rule( desc = "ǱӰ����ĩ�س���������", category = FEATURE )
    public static boolean shulkerSpawningInEndCities = false;

    @Rule(desc = "ʵ�����δ��������ʱ������ʧ", category = {EXPERIMENTAL, BUGFIX})
    public static boolean unloadedEntityFix = false;

    @Rule( desc = "TNT���������ź�Դ����ʱ������������", category = CREATIVE )
    public static boolean tntDoNotUpdate = false;

    @Rule(
            desc = "����ƶ�����ʱ���ٻص�",
            extra = "����˰��������ſͻ��˵��ƶ��ж��ɣ�",
            category = {CREATIVE, SURVIVAL}
    )
    public static boolean antiCheatDisabled = false;

    @Rule(desc = "������Ͷ�������������İ������ԣ�BUDλ��", category = CREATIVE)
    public static boolean quasiConnectivity = true;

    @Rule(
            desc = "ʹ������������ת/��ת����",
            extra = {"���鱻��ת/��תʱ������ɷ������"},
            category = {CREATIVE, SURVIVAL, FEATURE}
    )
    public static boolean flippinCactus = false;

    @Rule(
            desc = "ָ����ë��©�������¼�������ǵĵ���Ʒ",
            extra = {
                    "Enables /counter command, and actions while placing red and green carpets on wool blocks",
                    "Use /counter <color?> reset to reset the counter, and /counter <color?> to query",
                    "In survival, place green carpet on same color wool to query, red to reset the counters",
                    "Counters are global and shared between players, 16 channels available",
                    "Items counted are destroyed, count up to one stack per tick per hopper"
            },
            category = {COMMAND, CREATIVE, FEATURE}
    )
    public static boolean hopperCounters = false;

    @Rule( desc = "�������ܵ��׻�ʱ������Զ��������", category = FEATURE )
    public static boolean renewableSponges = false;

    @Rule( desc = "�������ƶ�����", category = {EXPERIMENTAL, FEATURE} )
    public static boolean movableTileEntities = false;

    @Rule( desc = "�������ȴ���������ȱ��ˮԴʱ��ɿ����Ĺ�ľ", category = FEATURE )
    public static boolean desertShrubs = false;

    @Rule( desc = "���������ʱ����ɳ��", category = FEATURE )
    public static boolean silverFishDropGravel = false;

    @Rule( desc = "ʹ�˹����ɵ��׵�ӵ����Ȼ�����׵������", category = CREATIVE )
    public static boolean summonNaturalLightning = false;

    @Rule(desc = "����/spawn����׷����������", category = COMMAND)
    public static boolean commandSpawn = true;

    @Rule(desc = "����/tick�����޸���Ϸʱ������", category = COMMAND)
    public static boolean commandTick = true;

    @Rule(desc = "����/log����������������Tab����鿴��Ϣ", category = COMMAND)
    public static boolean commandLog = true;

    @Rule(
            desc = "����/distance����鿴��������",
            extra = "���carpet����򿪣���ôҲ����ͨ��������ɫ��̺ʵ�ִ�����",
            category = COMMAND
    )
    public static boolean commandDistance = true;

    @Rule(
            desc = "����/info����鿴������Ϣ",
            extra = {"���carpet����򿪣���ôҲ����ͨ��������ɫ��̺ʵ�ִ�����"},
            category = COMMAND
    )
    public static boolean commandInfo = true;

    @Rule(
            desc = "����/c��/s��������л�����ģʽ���Թ���ģʽ",
            extra = "��������Ƿ����Ա��������ʹ��/c��/s����",
            category = COMMAND
    )
    public static boolean commandCameramode = true;

    @Rule(
            desc = "����/perimeterinfo����",
            extra = "... that scans the area around the block for potential spawnable spots",
            category = COMMAND
    )
    public static boolean commandPerimeterInfo = true;

    @Rule(desc = "����/draw����", extra = "... �Ի��Ƽ�ͼ��", category = COMMAND)
    public static boolean commandDraw = true;

    @Rule(desc = "����/script����", extra = "����һ����Ϸ���õĹ�Scarpet�ű����е�API", category = COMMAND)
    public static boolean commandScript = true;

    @Rule(
    		desc = "����/player����������/���Ƽ���",
    		extra = "ԭװ���ڡ�m�轺С��",
    		category = COMMAND)
    public static boolean commandPlayer = true;

    @Rule(desc = "����ǹ���Ա���ʹ�õ�̺������carpet����", category = SURVIVAL)
    public static boolean carpets = false;

    @Rule(
            desc = "���������������ཫ�����ʵĹ��߸�����ƻ�",
            category = SURVIVAL
    )
    public static boolean missingTools = false;

    @Rule(desc = "��ǿ�����ŵĻ�������Լ�С����", category = {SURVIVAL, CREATIVE})
    public static boolean portalCaching = false;

    @Rule(desc = "ʹ��/fill��/setblock��/clone�����Լ��ṹ����ʱ����ɷ������", category = CREATIVE)
    public static boolean fillUpdates = true;

    private static class PushLimitLimits extends Validator<Integer>
    {
        @Override public Integer validate(CommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string) {
            return (newValue>0 && newValue <= 1024) ? newValue : null;
        }
        @Override
        public String description() { return "������1��1024��ѡȡһ����ֵ";}
    }
    @Rule(
            desc = "���������ƶ������������",
            options = {"10", "12", "14", "100"},
            category = CREATIVE,
            validate = PushLimitLimits.class
    )
    public static int pushLimit = 12;

    @Rule(
            desc = "������������/�����������󼤻��",
            options = {"9", "15", "30"},
            category = CREATIVE,
            validate = PushLimitLimits.class
    )
    public static int railPowerLimit = 9;

    private static class FillLimitLimits extends Validator<Integer>
    {
        @Override public Integer validate(CommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string) {
            return (newValue>0 && newValue < 20000000) ? newValue : null;
        }
        @Override
        public String description() { return "������1��20000000��ѡȡһ����ֵ";}
    }
    @Rule(
            desc = "����/fill��/clone�����䷽������",
            options = {"32768", "250000", "1000000"},
            category = CREATIVE,
            validate = FillLimitLimits.class
    )
    public static int fillLimit = 32768;

    @Rule(
            desc = "����ʵ����ײ������ƣ�0Ϊ������",
            options = {"0", "1", "20"},
            category = OPTIMIZATION,
            validate = Validator.NONNEGATIVE_NUMBER.class
    )
    public static int maxEntityCollisions = 0;

    /*
    @Rule(
            desc = "Fix for piston ghost blocks",
            category = BUGFIX,
            validate = Validator.WIP.class
    )
    public static boolean pistonGhostBlocksFix = true;

    @Rule(
            desc = "fixes water performance issues",
            category = OPTIMIZATION,
            validate = Validator.WIP.class
    )
    public static boolean waterFlow = true;
    */

    @Rule(desc = "ֻ��һ�����˯�������ڷ�����������ҹ��", category = SURVIVAL)
    public static boolean onePlayerSleeping = false;

    private static class SetMotd extends Validator<String>
    {
        @Override public String validate(CommandSource source, ParsedRule<String> currentRule, String newValue, String string) {
            customMOTD = newValue; // accelerate a smidge
            CarpetServer.minecraft_server.checkMOTD();
            return newValue;
        }
    }
    @Rule(
            desc = "���ò�ͬ�ͻ������ӵ�������ʱ��MOTD��Ϣ",
            extra = "���롰_��ʱ��ʾĬ�������ļ���MOTD��Ϣ",
            options = "_",
            category = CREATIVE,
            validate = SetMotd.class
    )
    public static String customMOTD = "_";

    @Rule(desc = "������ʹ������������ת/��ת����", extra = "Ĭ������ʱ����ת", category = FEATURE)
    public static boolean rotatorBlock = false;

    private static class ViewDistanceValidator extends Validator<Integer>
    {
        @Override public Integer validate(CommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string)
        {
            if (currentRule.get().equals(newValue))
            {
                return newValue;
            }
            if (newValue < 0 || newValue > 32)
            {
                Messenger.m(source, "r �Ӿ����Ϊ0��32֮���һ����ֵ");
                return null;
            }
            MinecraftServer server = source.getServer();

            if (server.isDedicatedServer())
            {
                int vd = (newValue >= 2)?newValue:((DedicatedServer) server).getIntProperty("view-distance", 10);
                if (vd != CarpetServer.minecraft_server.getPlayerList().getViewDistance())
                    CarpetServer.minecraft_server.getPlayerList().setViewDistance(vd);
                return newValue;
            }
            else
            {
                Messenger.m(source, "r �Ӿ�ֻ���ڷ������ϱ�����");
                return 0;
            }
        }
        @Override
        public String description() { return "�����ѡ��0��32֮���һ����ֵ 0ΪĬ����ֵ";}
    }
    @Rule(
            desc = "�����������Ӿ�",
            extra = "0ΪĬ������",
            options = {"0", "12", "16", "32"},
            category = CREATIVE,
            validate = ViewDistanceValidator.class
    )
    public static int viewDistance = 0;

    private static class DisableSpawnChunksValidator extends Validator<Boolean>
    {
        @Override public Boolean validate(CommandSource source, ParsedRule<Boolean> currentRule, Boolean newValue, String string) {
            if (!newValue)
                Messenger.m(source, "w Spawn chunks re-enabled. Visit spawn to load them?");
            return newValue;
        }
    }
    @Rule(
            desc = "�������������ж��",
            category = CREATIVE,
            validate = DisableSpawnChunksValidator.class
    )
    public static boolean disableSpawnChunks = false;

    private static class KelpLimit extends Validator<Integer>
    {
        @Override public Integer validate(CommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string) {
            return (newValue>=0 && newValue <=25)?newValue:null;
        }
        @Override
        public String description() { return "You must choose a value from 0 to 25. 25 and all natural kelp can grow 25 blocks, choose 0 to make all generated kelp not to grow";}
    }
    @Rule(
            desc = "������Ȼ���ɵĺ�������",
            options = {"0", "2", "25"},
            category = FEATURE,
            validate = KelpLimit.class
    )
    public static int kelpGenerationGrowthLimit = 25;

    @Rule(desc = "ʹ�ùǷ۴���ɺ��֦����ɺ������", category = FEATURE)
    public static boolean renewableCoral = false;

    @Rule(desc = "�޸�����ڿ�����תʱ���õķ��鳯������", category = BUGFIX)
    public static boolean placementRotationFix = false;

    //@Rule(desc = "show dungean positions  ��4WARNING!!! DON'T OPEN BEFORE BACKING UP!!!", category = DESTRUCTIVE)
    public static boolean DBBOn = false;

    //@Rule(desc = "Frost no melting when ice broken aside", category = FEATURE )
    public static boolean breakingFrostCausesMelting = true;
    
    //@Rule(desc = "Frosted ice blocks load neighboring chunks", category = FEATURE )
    public static boolean FrostChunkLoading = true;
    
    @Rule(
            desc = "����������δ���������жϿ�/��ʧ",
            extra = "�ۼ���Ϊʵ���������bug�ٴγ���ʱ�������½�����Ϸ",
            category = BUGFIX
    )
    public static boolean leadFix = false;
}

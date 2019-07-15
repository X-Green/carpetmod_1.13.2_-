package carpet.commands;

import carpet.CarpetServer;
import carpet.settings.CarpetSettings;
import carpet.settings.ParsedRule;
import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;
import static net.minecraft.command.ISuggestionProvider.suggest;

public class CarpetCommand
{
    private static ParsedRule<?> getRule(CommandContext<CommandSource> ctx) throws CommandSyntaxException
    {
        String ruleName = StringArgumentType.getString(ctx, "rule");
        ParsedRule<?> rule = SettingsManager.getRule(ruleName);
        if (rule == null)
            throw new SimpleCommandExceptionType(Messenger.c("rb Unknown rule: "+ruleName)).create();
        return rule;
    }
    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        LiteralArgumentBuilder<CommandSource> literalargumentbuilder = literal("carpet").requires((player) ->
                player.hasPermissionLevel(2) && !CarpetServer.settingsManager.locked);

        literalargumentbuilder.executes((context)->listAllSettings(context.getSource())).
                then(literal("list").
                        executes( (c) -> listSettings(c.getSource(),
                                "���е�Carpet Mod����ѡ��",
                                SettingsManager.getRules())).
                        then(literal("defaults").
                                executes( (c)-> listSettings(c.getSource(),
                                        "��carpet.conf�м���������Ϣ",
                                        CarpetServer.settingsManager.findStartupOverrides()))).
                        then(argument("tag",StringArgumentType.word()).
                                suggests( (c, b)->suggest(SettingsManager.getCategories(), b)).
                                executes( (c) -> listSettings(c.getSource(),
                                        String.format("CarpetModѡ��: \"%s\"", GetDisplayName(StringArgumentType.getString(c, "tag"))),
                                        CarpetServer.settingsManager.getRulesMatching(StringArgumentType.getString(c, "tag")))))).
                then(literal("removeDefault").
                        requires(s -> !CarpetServer.settingsManager.locked).
                        then(argument("rule", StringArgumentType.word()).
                                suggests( (c, b) -> suggest(SettingsManager.getRules().stream().map(r -> r.name), b)).
                                executes((c) -> removeDefault(c.getSource(), getRule(c))))).
                then(literal("setDefault").
                        requires(s -> !CarpetServer.settingsManager.locked).
                        then(argument("rule", StringArgumentType.word()).
                                suggests( (c, b) -> suggest(SettingsManager.getRules().stream().map(r -> r.name), b)).
                                then(argument("value", StringArgumentType.word()).
                                        suggests((c, b)-> suggest(getRule(c).options, b)).
                                        executes((c) -> setDefault(c.getSource(), getRule(c), StringArgumentType.getString(c, "value")))))).
                then(argument("rule", StringArgumentType.word()).
                        suggests( (c, b) -> suggest(SettingsManager.getRules().stream().map(r -> r.name), b)).
                        requires(s -> !CarpetServer.settingsManager.locked ).
                        executes( (c) -> displayRuleMenu(c.getSource(),getRule(c))).
                        then(argument("value", StringArgumentType.word()).
                                suggests((c, b)-> suggest(getRule(c).options,b)).
                                executes((c) -> setRule(c.getSource(), getRule(c), StringArgumentType.getString(c, "value")))));

        dispatcher.register(literalargumentbuilder);
    }

    private static int displayRuleMenu(CommandSource source, ParsedRule<?> rule)
    {
    	
        EntityPlayer player;
        try
        {
            player = source.asPlayer();
        }
        catch (CommandSyntaxException e)
        {
            Messenger.m(source, "w "+rule.name +" ������Ϊ ","wb "+rule.getAsString());
            return 1;
        }

        Messenger.m(player, "");
        Messenger.m(player, "wb "+rule.name,"!/carpet "+rule.name,"^g ˢ��");
        Messenger.m(player, "w "+rule.description);

        rule.extraInfo.forEach(s -> Messenger.m(player, "g " + s));

        List<ITextComponent> tags = new ArrayList<>();
        tags.add(Messenger.c("w Tags: "));
        for (String t: rule.categories)
        {
            tags.add(Messenger.c("c ["+GetDisplayName(t)+"]", "^g �г�����["+GetDisplayName(t)+"]ѡ��","!/carpet list "+t));
            tags.add(Messenger.c("w , "));
        }
        tags.remove(tags.size()-1);
        Messenger.m(player, tags.toArray(new Object[0]));

        Messenger.m(player, "w Ŀǰֵ: ",String.format("%s %s (%sֵ)",rule.getBoolValue()?"lb":"nb", rule.getAsString(),rule.isDefault()?"Ĭ��":"�޸Ĺ���"));
        List<ITextComponent> options = new ArrayList<>();
        options.add(Messenger.c("w ѡ��: ", "y [ "));
        for (String o: rule.options)
        {
            options.add(Messenger.c(makeSetRuleButton(rule, o, false)));
            options.add(Messenger.c("w  "));
        }
        options.remove(options.size()-1);
        options.add(Messenger.c("y  ]"));
        Messenger.m(player, options.toArray(new Object[0]));

        return 1;
    }

    private static int setRule(CommandSource source, ParsedRule<?> rule, String newValue)
    {
        if (rule.set(source, newValue) != null)
            Messenger.m(source, "w "+rule.toString()+", ", "c [�˳���Ϸ����������ģ�]",
                    "^w ����˴�������д��carpet.conf�ļ�������һ�δ���Ϸ/������ʱ��ȡ",
                    "?/carpet setDefault "+rule.name+" "+rule.getAsString());
        return 1;
    }
    private static int setDefault(CommandSource source, ParsedRule<?> rule, String defaultValue)
    {
        if (CarpetServer.settingsManager.setDefaultRule(source, rule.name, defaultValue))
            Messenger.m(source ,"gi ����"+ rule.name+" ����Ĭ��Ϊ "+ defaultValue);
        return 1;
    }
    private static int removeDefault(CommandSource source, ParsedRule<?> rule)
    {
        if (CarpetServer.settingsManager.removeDefaultRule(source, rule.name))
            Messenger.m(source ,"gi rule "+ rule.name+" ԭ��Ĭ��ѡ��");
        return 1;
    }


    private static ITextComponent displayInteractiveSetting(ParsedRule<?> e)
    {
        List<Object> args = new ArrayList<>();
        args.add("w - "+e.name+" ");
        args.add("!/carpet "+e.name);
        args.add("^y "+e.description);
        for (String option: e.options)
        {
            args.add(makeSetRuleButton(e, option, true));
            args.add("w  ");
        }
        args.remove(args.size()-1);
        return Messenger.c(args.toArray(new Object[0]));
    }

    private static ITextComponent makeSetRuleButton(ParsedRule<?> rule, String option, boolean brackets)
    {
        String style = rule.isDefault()?"g":(option.equalsIgnoreCase(rule.defaultAsString)?"y":"e");
        if (option.equalsIgnoreCase(rule.defaultAsString))
            style = style+"b";
        else if (option.equalsIgnoreCase(rule.getAsString()))
            style = style+"u";
        String baseText = style + (brackets ? " [" : " ") + option + (brackets ? "]" : "");
        if (CarpetServer.settingsManager.locked)
            return Messenger.c(baseText, "^g����������");
        if (option.equalsIgnoreCase(rule.getAsString()))
            return Messenger.c(baseText);
        return Messenger.c(baseText, "^g ѡ���� " + option, "?/carpet " + rule.name + " " + option);
    }

    private static int listSettings(CommandSource source, String title, Collection<ParsedRule<?>> settings_list)
    {
        try
        {
            EntityPlayer player = source.asPlayer();
            Messenger.m(player,String.format("wb %s:",title));
            settings_list.forEach(e -> Messenger.m(player,displayInteractiveSetting(e)));

        }
        catch (CommandSyntaxException e)
        {
            Messenger.m(source, "w s:"+title);
            settings_list.forEach(r -> Messenger.m(source, "w  - "+ r.toString()));
        }
        return 1;
    }
    private static int listAllSettings(CommandSource source)
    {
        listSettings(source, "ĿǰCarpet Mod����ѡ��", CarpetServer.settingsManager.getNonDefault());

        Messenger.m(source, "Carpet Mod�汾: "+CarpetSettings.carpetVersion);
        try
        {
            EntityPlayer player = source.asPlayer();
            List<Object> tags = new ArrayList<>();
            tags.add("w ��lѡ��Ŀ¼:\n");
            for (String t : SettingsManager.getCategories())
            {
            	
                tags.add("c [" + GetDisplayName(t)+"]");
                tags.add("^g �г�����[" + GetDisplayName(t) + "]ѡ��");
                tags.add("!/carpet list " + t);
                tags.add("w  ");
            }
            tags.remove(tags.size() - 1);
            Messenger.m(player, tags.toArray(new Object[0]));
        }
        catch (CommandSyntaxException e)
        {
        }
        return 1;
    }
    
    public static String GetDisplayName(String nameOfCategory) 
    {
    	//StringBuffer DisplayName = new StringBuffer(null);
    	switch(nameOfCategory)
    	{
    		case "bugfix":
    			//DisplayName.append("bug�޸�");
    			String a = "bug�޸�";
    			return a;
    		case "survival":
    			//DisplayName.append("����ģʽ");
    			String b = "����ģʽ";
    			return b;
    		case "creative":
    			//DisplayName.append("����ģʽ");
    			String c = "����ģʽ";
    			return c;
    		case "experimental":
    			//DisplayName.append("ʵ���Եģ����ܲ��ȶ���");
    			String d = "ʵ���Ե�";
    			return d;
    		case "optimization":
    			//DisplayName.append("�Ż�");
    			String e = "�Ż�";
    			return e;
    		case "feature":
    			//DisplayName.append("����");
    			String f = "����";
    			return f;
    		case "command":
    			//DisplayName.append("����");
    			String g = "����";
    			return g;
    		case "destructive":
    			//DisplayName.append("�����Ե�");
    			String h = "�����Ե�";
    			return h;
    		default :
    			//DisplayName.append("δ֪");
    			String i = "δ֪";
    			return i;
    	}
    	
    	
    }
}

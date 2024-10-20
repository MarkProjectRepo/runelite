from py4j.java_gateway import JavaGateway, JavaObject

class Prayer:
    def __init__(self, gateway):
        self.gateway = gateway
        self.java_prayer = gateway.jvm.net.runelite.api.Prayer

    def __getattr__(self, name):
        return getattr(self.java_prayer, name)

    THICK_SKIN = None
    BURST_OF_STRENGTH = None
    CLARITY_OF_THOUGHT = None
    SHARP_EYE = None
    MYSTIC_WILL = None
    ROCK_SKIN = None
    SUPERHUMAN_STRENGTH = None
    IMPROVED_REFLEXES = None
    RAPID_RESTORE = None
    RAPID_HEAL = None
    PROTECT_ITEM = None
    HAWK_EYE = None
    MYSTIC_LORE = None
    STEEL_SKIN = None
    ULTIMATE_STRENGTH = None
    INCREDIBLE_REFLEXES = None
    PROTECT_FROM_MAGIC = None
    PROTECT_FROM_MISSILES = None
    PROTECT_FROM_MELEE = None
    EAGLE_EYE = None
    MYSTIC_MIGHT = None
    RETRIBUTION = None
    REDEMPTION = None
    SMITE = None
    CHIVALRY = None
    PIETY = None
    PRESERVE = None
    RIGOUR = None
    AUGURY = None
    RP_REJUVENATION = None
    RP_ANCIENT_STRENGTH = None
    RP_ANCIENT_SIGHT = None
    RP_ANCIENT_WILL = None
    RP_PROTECT_ITEM = None
    RP_RUINOUS_GRACE = None
    RP_DAMPEN_MAGIC = None
    RP_DAMPEN_RANGED = None
    RP_DAMPEN_MELEE = None
    RP_TRINITAS = None
    RP_BERSERKER = None
    RP_PURGE = None
    RP_METABOLISE = None
    RP_REBUKE = None
    RP_VINDICATION = None
    RP_DECIMATE = None
    RP_ANNIHILATE = None
    RP_VAPORISE = None
    RP_FUMUS_VOW = None
    RP_UMBRA_VOW = None
    RP_CRUORS_VOW = None
    RP_GLACIES_VOW = None
    RP_WRATH = None
    RP_INTENSIFY = None

    @staticmethod
    def initialize(gateway):
        Prayer.THICK_SKIN = gateway.jvm.net.runelite.api.Prayer.THICK_SKIN
        Prayer.BURST_OF_STRENGTH = gateway.jvm.net.runelite.api.Prayer.BURST_OF_STRENGTH
        Prayer.CLARITY_OF_THOUGHT = gateway.jvm.net.runelite.api.Prayer.CLARITY_OF_THOUGHT
        Prayer.SHARP_EYE = gateway.jvm.net.runelite.api.Prayer.SHARP_EYE
        Prayer.MYSTIC_WILL = gateway.jvm.net.runelite.api.Prayer.MYSTIC_WILL
        Prayer.ROCK_SKIN = gateway.jvm.net.runelite.api.Prayer.ROCK_SKIN
        Prayer.SUPERHUMAN_STRENGTH = gateway.jvm.net.runelite.api.Prayer.SUPERHUMAN_STRENGTH
        Prayer.IMPROVED_REFLEXES = gateway.jvm.net.runelite.api.Prayer.IMPROVED_REFLEXES
        Prayer.RAPID_RESTORE = gateway.jvm.net.runelite.api.Prayer.RAPID_RESTORE
        Prayer.RAPID_HEAL = gateway.jvm.net.runelite.api.Prayer.RAPID_HEAL
        Prayer.PROTECT_ITEM = gateway.jvm.net.runelite.api.Prayer.PROTECT_ITEM
        Prayer.HAWK_EYE = gateway.jvm.net.runelite.api.Prayer.HAWK_EYE
        Prayer.MYSTIC_LORE = gateway.jvm.net.runelite.api.Prayer.MYSTIC_LORE
        Prayer.STEEL_SKIN = gateway.jvm.net.runelite.api.Prayer.STEEL_SKIN
        Prayer.ULTIMATE_STRENGTH = gateway.jvm.net.runelite.api.Prayer.ULTIMATE_STRENGTH
        Prayer.INCREDIBLE_REFLEXES = gateway.jvm.net.runelite.api.Prayer.INCREDIBLE_REFLEXES
        Prayer.PROTECT_FROM_MAGIC = gateway.jvm.net.runelite.api.Prayer.PROTECT_FROM_MAGIC
        Prayer.PROTECT_FROM_MISSILES = gateway.jvm.net.runelite.api.Prayer.PROTECT_FROM_MISSILES
        Prayer.PROTECT_FROM_MELEE = gateway.jvm.net.runelite.api.Prayer.PROTECT_FROM_MELEE
        Prayer.EAGLE_EYE = gateway.jvm.net.runelite.api.Prayer.EAGLE_EYE
        Prayer.MYSTIC_MIGHT = gateway.jvm.net.runelite.api.Prayer.MYSTIC_MIGHT
        Prayer.RETRIBUTION = gateway.jvm.net.runelite.api.Prayer.RETRIBUTION
        Prayer.REDEMPTION = gateway.jvm.net.runelite.api.Prayer.REDEMPTION
        Prayer.SMITE = gateway.jvm.net.runelite.api.Prayer.SMITE
        Prayer.CHIVALRY = gateway.jvm.net.runelite.api.Prayer.CHIVALRY
        Prayer.PIETY = gateway.jvm.net.runelite.api.Prayer.PIETY
        Prayer.PRESERVE = gateway.jvm.net.runelite.api.Prayer.PRESERVE
        Prayer.RIGOUR = gateway.jvm.net.runelite.api.Prayer.RIGOUR
        Prayer.AUGURY = gateway.jvm.net.runelite.api.Prayer.AUGURY
        Prayer.RP_REJUVENATION = gateway.jvm.net.runelite.api.Prayer.RP_REJUVENATION
        Prayer.RP_ANCIENT_STRENGTH = gateway.jvm.net.runelite.api.Prayer.RP_ANCIENT_STRENGTH
        Prayer.RP_ANCIENT_SIGHT = gateway.jvm.net.runelite.api.Prayer.RP_ANCIENT_SIGHT
        Prayer.RP_ANCIENT_WILL = gateway.jvm.net.runelite.api.Prayer.RP_ANCIENT_WILL
        Prayer.RP_PROTECT_ITEM = gateway.jvm.net.runelite.api.Prayer.RP_PROTECT_ITEM
        Prayer.RP_RUINOUS_GRACE = gateway.jvm.net.runelite.api.Prayer.RP_RUINOUS_GRACE
        Prayer.RP_DAMPEN_MAGIC = gateway.jvm.net.runelite.api.Prayer.RP_DAMPEN_MAGIC
        Prayer.RP_DAMPEN_RANGED = gateway.jvm.net.runelite.api.Prayer.RP_DAMPEN_RANGED
        Prayer.RP_DAMPEN_MELEE = gateway.jvm.net.runelite.api.Prayer.RP_DAMPEN_MELEE
        Prayer.RP_TRINITAS = gateway.jvm.net.runelite.api.Prayer.RP_TRINITAS
        Prayer.RP_BERSERKER = gateway.jvm.net.runelite.api.Prayer.RP_BERSERKER
        Prayer.RP_PURGE = gateway.jvm.net.runelite.api.Prayer.RP_PURGE
        Prayer.RP_METABOLISE = gateway.jvm.net.runelite.api.Prayer.RP_METABOLISE
        Prayer.RP_REBUKE = gateway.jvm.net.runelite.api.Prayer.RP_REBUKE
        Prayer.RP_VINDICATION = gateway.jvm.net.runelite.api.Prayer.RP_VINDICATION
        Prayer.RP_DECIMATE = gateway.jvm.net.runelite.api.Prayer.RP_DECIMATE
        Prayer.RP_ANNIHILATE = gateway.jvm.net.runelite.api.Prayer.RP_ANNIHILATE
        Prayer.RP_VAPORISE = gateway.jvm.net.runelite.api.Prayer.RP_VAPORISE
        Prayer.RP_FUMUS_VOW = gateway.jvm.net.runelite.api.Prayer.RP_FUMUS_VOW
        Prayer.RP_UMBRA_VOW = gateway.jvm.net.runelite.api.Prayer.RP_UMBRA_VOW
        Prayer.RP_CRUORS_VOW = gateway.jvm.net.runelite.api.Prayer.RP_CRUORS_VOW
        Prayer.RP_GLACIES_VOW = gateway.jvm.net.runelite.api.Prayer.RP_GLACIES_VOW
        Prayer.RP_WRATH = gateway.jvm.net.runelite.api.Prayer.RP_WRATH
        Prayer.RP_INTENSIFY = gateway.jvm.net.runelite.api.Prayer.RP_INTENSIFY

    def get_varbit(self, prayer):
        return prayer.getVarbit()

if __name__ == "__main__":
    gateway = JavaGateway()
    Prayer.initialize(gateway)
    prayer = Prayer(gateway)
    print(Prayer.THICK_SKIN)
    thick_skin_varbit = prayer.get_varbit(Prayer.THICK_SKIN)
    print(f"Thick Skin varbit: {thick_skin_varbit}")
    print(dir(Prayer.THICK_SKIN))
    print(Prayer.THICK_SKIN.values)
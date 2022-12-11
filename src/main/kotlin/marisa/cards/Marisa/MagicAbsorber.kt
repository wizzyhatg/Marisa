package marisa.cards.Marisa

import marisa.patches.AbstractCardEnum
import basemod.abstracts.CustomCard
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.AbstractPower

class MagicAbsorber : CustomCard(
    ID,
    NAME,
    IMG_PATH,
    COST,
    DESCRIPTION,
    CardType.SKILL,
    AbstractCardEnum.MARISA_COLOR,
    CardRarity.UNCOMMON,
    CardTarget.SELF
) {
    init {
        baseBlock = BLOCK_AMT
        baseMagicNumber = 1
        magicNumber = baseMagicNumber
        exhaust = true
    }

    override fun use(p: AbstractPlayer, unused: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(
            GainBlockAction(p, p, block)
        )
        if (!p.powers.isEmpty()) {
            val pows = ArrayList<AbstractPower>()
            for (pow in p.powers) {
                if (pow.type == AbstractPower.PowerType.DEBUFF) {
                    pows.add(pow)
                }
            }
            if (!pows.isEmpty()) {
                val po = pows[AbstractDungeon.miscRng.random(0, pows.size - 1)]
                AbstractDungeon.actionManager.addToBottom(
                    RemoveSpecificPowerAction(p, p, po)
                )
            }
        }
    }

    override fun makeCopy(): AbstractCard {
        return MagicAbsorber()
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBlock(UPGRADE_PLUS_BLOCK)
        }
    }

    companion object {
        const val ID = "MagicAbsorber"
        const val IMG_PATH = "img/cards/MagicAbsorber.png"
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(ID)
        val NAME = cardStrings.NAME
        val DESCRIPTION = cardStrings.DESCRIPTION
        private const val COST = 1
        private const val BLOCK_AMT = 8
        private const val UPGRADE_PLUS_BLOCK = 3
    }
}

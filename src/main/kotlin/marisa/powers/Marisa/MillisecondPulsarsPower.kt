package marisa.powers.Marisa

import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.powers.AbstractPower

class MillisecondPulsarsPower(owner: AbstractCreature?) : AbstractPower() {
    init {
        name = NAME
        ID = POWER_ID
        this.owner = owner
        amount = -1
        type = PowerType.BUFF
        updateDescription()
        img = Texture("img/powers/steadyPulse.png")
    }

    override fun stackPower(stackAmount: Int) {}
    override fun updateDescription() {
        description = DESCRIPTIONS[0]
    }

    companion object {
        const val POWER_ID = "MilliPulsaPower"
        private val powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID)
        val NAME = powerStrings.NAME
        val DESCRIPTIONS = powerStrings.DESCRIPTIONS
    }
}
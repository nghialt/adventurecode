package main.kotlin

class Player(val index: Int, var cards: MutableList<Int>) {

    fun getTopCard(): Int {
        return cards.removeAt(0)
    }

    fun addCards(vararg addedCards: Int) {
        addedCards.forEach { cards.add(it) }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Player) return false
        if (index != other.index) return false
        if (cards != other.cards) return false
        return true
    }
}
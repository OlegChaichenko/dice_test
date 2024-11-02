import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {

    private val _diceValues = MutableLiveData<List<Int>>()
    val diceValues: LiveData<List<Int>> get() = _diceValues

    private val handler = Handler(Looper.getMainLooper())
    private val diceRollRunnable = object : Runnable {
        override fun run() {
            rollDice()
            handler.postDelayed(this, 100)
        }
    }

    private var isRolling = false

    fun startRollingDice() {
        if (!isRolling) {
            isRolling = true
            handler.post(diceRollRunnable)
        }
    }

    fun stopRollingDice() {
        isRolling = false
        handler.removeCallbacks(diceRollRunnable)
    }

    private fun rollDice() {
        val newValues = List(5) { Random.nextInt(1, 7) }
        _diceValues.value = newValues
    }
}

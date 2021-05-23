package josip.cukovic.soundpoolapp

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

private lateinit var mSoundPool: SoundPool
var mSoundMap: HashMap<Int, Int> = HashMap()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setUpUi()
        this.loadSounds()
    }


    private fun setUpUi() {
        findViewById<ImageButton>(R.id.diceBtn).setOnClickListener{ playSound(R.raw.diceroll)}
        findViewById<ImageButton>(R.id.bellBtn).setOnClickListener{playSound(R.raw.servicebell)}
        findViewById<ImageButton>(R.id.vanSlideDoorBtn).setOnClickListener{playSound(R.raw.vanslidingdoor)}
    }
    private fun loadSounds() {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            mSoundPool = SoundPool(3, AudioManager.STREAM_MUSIC, 0)
        }
        mSoundMap[R.raw.diceroll] = mSoundPool.load(this, R.raw.diceroll, 1)
        mSoundMap[R.raw.servicebell] = mSoundPool.load(this, R.raw.servicebell, 1)
        mSoundMap[R.raw.vanslidingdoor] = mSoundPool.load(this, R.raw.vanslidingdoor, 1)
    }
    fun playSound(selectedSound: Int) {
        val soundID = mSoundMap[selectedSound] ?: 0
        mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }

}
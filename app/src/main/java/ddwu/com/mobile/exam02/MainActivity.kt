package ddwu.com.mobile.exam02

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import ddwu.com.mobile.exam02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    val size = 10
    var colorSelection = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerForContextMenu(binding.myCustomView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.bigger -> binding.myCustomView.r += size
            R.id.smaller -> binding.myCustomView.r -= size
        }
        binding.myCustomView.invalidate()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_color, menu)
//        context menu 표시 시 선택한 라디오버튼 check
        when (colorSelection) {
            0 -> menu?.findItem(R.id.item_red)?.isChecked = true
            1 -> menu?.findItem(R.id.item_green)?.isChecked= true
            2 -> menu?.findItem(R.id.item_blue)?.isChecked = true
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_red -> {
                binding.myCustomView.paintColor = Color.RED
                colorSelection = 0
            }
            R.id.item_green -> {
                binding.myCustomView.paintColor = Color.GREEN
                colorSelection = 1
            }
            R.id.item_blue -> {
                binding.myCustomView.paintColor = Color.BLUE
                colorSelection = 2
            }
        }
        binding.myCustomView.invalidate()
        return super.onContextItemSelected(item)
    }
}
package fr.isen.liautaud.partagetonsavoir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import fr.isen.liautaud.partagetonsavoir.registration.UserActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val goToLoginAction = findViewById<Button>(R.id.start)
        goToLoginAction.setOnClickListener {
            startActivity(Intent(this,UserActivity::class.java))
        }
    }
}
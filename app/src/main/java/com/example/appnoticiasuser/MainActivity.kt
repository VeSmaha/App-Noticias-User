package com.example.appnoticiasuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.appnoticiasuser.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {


    //layout main dentro da var binding
    private lateinit var binding : ActivityMainBinding

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RecuperaDados()



    }
    private fun RecuperaDados(){
        db.collection("noticias").document("noticia").get()
            .addOnCompleteListener{ documento ->
                if(documento.isSuccessful){
                    val titulo = documento.result.get("titulo").toString()
                    val noticia = documento.result.get("noticia").toString()
                    val data = documento.result.get("data").toString()
                    val autor = documento.result.get("autor").toString()

                    binding.textViewNoticias.text = titulo
                    binding.textNoticia.text = noticia
                    binding.dataNoticia.text = data
                    binding.autorNoticia.text = autor
                }

            }
    }
}
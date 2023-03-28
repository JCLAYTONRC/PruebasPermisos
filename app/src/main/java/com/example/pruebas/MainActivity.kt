package com.example.pruebas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.example.pruebas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object{
        private const val REQUEST_MANAGE_EXTERNAL_STORAGE = 123
    }

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mBinding.btnAction.setOnClickListener {
           // requestMyPermissions()

            if(checkSelfPermission(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION) == PackageManager.PERMISSION_GRANTED){
                log("Permiso activo")
            } else {
                requestMyPermissionsV2()
            }

        }
    }

    private fun requestMyPermissionsV2() {
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        intent.data = Uri.parse("package:$packageName")
        startActivityForResult(intent, REQUEST_MANAGE_EXTERNAL_STORAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_MANAGE_EXTERNAL_STORAGE){
            if ( checkSelfPermission(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION) == PackageManager.PERMISSION_GRANTED){
                log("El usuario concedio el permiso")

            } else {
                log("El usuario rechazo el permiso")
            }

        }
    }

/*

    private fun requestMyPermissions() {
        if( ContextCompat.checkSelfPermission(this,Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            // El permiso no esa aceptado
            requestThePermissions()
        } else {
            // El permiso esta aceptado
            log("El permiso esta aceptado")
        }

        */
/*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()){
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }*//*


    }

    private fun requestThePermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE)){
            log("El usuario ha rechazado el permiso anteriormente")
            //El usuario ya ha rechazado el permiso anteriormente, debemos informarle que vaya a ajustes
        } else {
            log("EL usuario nunca ha aceptado ni rechazado los permisos")
            //El usuario nunca ha aceptado ni rechazado los permisos asi que lo pediremos
            ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE), 0)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            0 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    log("El usuario ha aceptado el permiso y no tiene porque darle de nuevo")
                    //El usuario ha aceptado el permiso, no tiene porqué darle de nuevo al botón, podemos lanzar la funcionalidad desde aquí.
                } else {
                    log("El usuario rechazo el permiso")
                    //El usuario ha rechazado el permiso, podemos desactivar la funcionalidad o mostrar una vista/diálogo.
                }
                return
            }
            else -> {
                // Este else lo dejamos por si sale un permiso que no teníamos controlado.

            }
        }
    }
*/

    private fun log(msg: String){
        android.util.Log.i("PRUEBAS",msg)
    }
}
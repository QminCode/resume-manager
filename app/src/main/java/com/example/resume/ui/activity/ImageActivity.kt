package com.example.resume.ui.activity

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import coil.load
import com.example.resume.R
import com.example.resume.base.BaseActivity
import com.example.resume.databinding.ActivityImageBinding
import com.example.resume.ui.activity.viewModel.EmptyViewModel

class ImageActivity : BaseActivity<EmptyViewModel, ActivityImageBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        val image = bundle?.getString("image")
        mBind.imageView.load(image)
    }
}
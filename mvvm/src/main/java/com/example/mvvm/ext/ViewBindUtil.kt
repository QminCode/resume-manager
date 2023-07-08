package com.example.mvvm.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.mvvm.base.BaseViewModel
import com.example.mvvm.base.BaseVmActivity
import com.example.mvvm.base.BaseVmFragment
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * 作者　: hegaojian
 * 时间　: 2021/12/21
 * 描述　: ViewBinding DataBinding 反射
 */

@JvmName("inflateBinding")
fun <VB : ViewBinding> AppCompatActivity.inflateBinding(): VB =
    bindingClass<VB>(this) { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
    }.also { binding ->
        if (binding is ViewDataBinding) {
            binding.lifecycleOwner = this
        }
    }

@JvmName("inflateBinding")
fun <VB : ViewBinding> Fragment.inflateBinding(layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): VB =
    bindingClass<VB>(this) { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            .invoke(null, layoutInflater, parent, attachToParent) as VB
    }.also { binding ->
        if (binding is ViewDataBinding) {
            binding.lifecycleOwner = viewLifecycleOwner
        }
    }

private fun <VB : ViewBinding> bindingClass(any: Any, block: (Class<VB>) -> VB): VB {
    var genericSuperclass = any.javaClass.genericSuperclass
    var superclass = any.javaClass.superclass
    while (superclass != null) {
        if (genericSuperclass is ParameterizedType) {
                try {
                    return block.invoke(genericSuperclass.actualTypeArguments[1] as Class<VB>)
                } catch (e: NoSuchMethodException) {
                } catch (e: ClassCastException) {
                } catch (e: InvocationTargetException) {
                    throw e.targetException
                }
        }
        genericSuperclass = superclass.genericSuperclass
        superclass = superclass.superclass
    }
    throw IllegalArgumentException("没有找到ViewBinding泛型")
}

/**
 * 创建viewModel
 */
fun <VM: com.example.mvvm.base.BaseViewModel> com.example.mvvm.base.BaseVmActivity<VM>.createViewModel(): VM {
    return ViewModelProvider(this)[getVmClazz(this)]
}

/**
 * 创建viewModel
 */
fun <VM: com.example.mvvm.base.BaseViewModel> BaseVmFragment<VM>.createViewModel(): VM {
    return ViewModelProvider(this)[getVmClazz(this)]
}
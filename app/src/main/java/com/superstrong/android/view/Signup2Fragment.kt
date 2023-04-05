package com.superstrong.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.superstrong.android.R
import com.superstrong.android.databinding.FragmentSignup2Binding
import com.superstrong.android.viewmodel.SignupVModel

/**
 * A simple [Fragment] subclass.
 * Use the [Signup2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Signup2Fragment : Fragment() {

    private var _binding: FragmentSignup2Binding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val vmodel: SignupVModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignup2Binding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmodel.error_code.observe(viewLifecycleOwner, Observer {
            val value = vmodel.error_code.value
            if(value==1)
                binding.inputError.text=""
            else if(value==2)
                binding.inputError.text="이미 가입된 아이디가 있습니다."
            else if(value==3)
                binding.inputError.text="비밀번호 불일치."
            else if(value==4)
                binding.inputError.text="이메일 인증이 끝나지 않은 계정입니다."
            else if(value==5)
                binding.inputError.text="비어있는 항목이 있습니다."
        })

        binding.confirmButton.setOnClickListener{
            vmodel.signupRequst(binding.ideditText.text.toString(), binding.passeditText.text.toString(),
                binding.passeditText2.text.toString(), binding.emailedit.text.toString(),
                binding.regedit.text.toString(),binding.numedit.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
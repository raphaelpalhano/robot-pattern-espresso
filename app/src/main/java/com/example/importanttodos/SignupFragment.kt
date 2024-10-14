package com.example.importanttodos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.importanttodos.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        // Inicializa o ViewModel e o DAO
        val application = requireNotNull(this.activity).application
        val dao = AppDatabase.getInstance(application).userDao
        val viewModelFactory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignupViewModel::class.java]

        // Configura o binding e o ciclo de vida
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Observa a navegação para a tela de login após o cadastro
        viewModel.navigateToLogin.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
                findNavController().navigate(action)
                viewModel.onNavigatedToLogin()
            }
        }

        // Observa mensagens de erro
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                binding.errorMessage.text = it
            }
        }

        // Configura o listener do botão de cadastro
        binding.signupButton.setOnClickListener {
            val name = binding.nameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signup(name, email, password)
            } else {
                viewModel.errorMessage.value = "Por favor, preencha todos os campos"
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

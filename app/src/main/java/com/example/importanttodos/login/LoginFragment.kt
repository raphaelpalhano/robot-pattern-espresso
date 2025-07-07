package com.example.importanttodos.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.importanttodos.data.DatabaseProvider
import com.example.importanttodos.factory.UserViewModelFactory
import com.example.importanttodos.databinding.LoginUserBinding

class LoginFragment : Fragment() {

    private var _binding: LoginUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginUserBinding.inflate(inflater, container, false)

        // Inicializa o ViewModel e o DAO
        val application = requireNotNull(this.activity).application
        val dao = DatabaseProvider.getDatabase(application).userDao
        val viewModelFactory = UserViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        // Configura o binding e o ciclo de vida
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Observa a navegação para a lista de tarefas
        viewModel.navigateToList.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                val action = LoginFragmentDirections.actionLoginFragmentToTodosFragment()
                findNavController().navigate(action)
                viewModel.onNavigatedToList()
            }
        }

        binding.signupLink.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }

        // Observa mensagens de erro
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                // Exibe a mensagem de erro no TextView ou via Toast
                binding.errorMessage.text = it
                // Ou, se preferir usar Toast:
                // Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        // Configura o listener do botão de login
        binding.siginupButton.setOnClickListener { // Renomeie para 'loginButton' se preferir
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Tenta o login chamando o ViewModel
                viewModel.login(email, password)
            } else {
                viewModel.setErrorMessage("Por favor, preencha todos os campos")
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { LoginResponse } from '../model/LoginResponse';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  LoginForm : FormGroup;
  erroMessage: string |null=null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ){
    this.LoginForm=this.fb.group({
      email: ['',[Validators.required, Validators.email]],
      password: ['',[Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(){
    if (this.LoginForm.valid){
      const credentials=this.LoginForm.value;
      this.authService.login(credentials).subscribe({
        next: (response: LoginResponse) =>{
          this.erroMessage=null;
          if (response.status=='success'){
            this.authService.setToken(response.data.token);
            this.authService.setUserData(response.data);
            this.router.navigate(['/home']);
          }else{
            this.erroMessage= response.message || 'Login failed. '
          }
        },
        error: (error) => {
          this.erroMessage= error.error?.message || 'An error occured.Please try again. '
          console.error('Login error:', error);
        }
        
      })
    }
  }

}

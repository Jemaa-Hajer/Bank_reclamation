import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { Client } from '../model/client.model';
import { SignUpResponse } from '../model/SignUpResponse';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signUpForm: FormGroup;
  errorMessage: string | null=null;

  constructor(
    private fb: FormBuilder,
    private authService : AuthService,
    private router : Router
  ){
    this.signUpForm=this.fb.group({
      name: ['',[Validators.required, Validators.minLength(2)]],
      surname: ['',[Validators.required, Validators.minLength(2)]],
      email : ['',[Validators.required, Validators.email]],
      mobile: ['', [Validators.required, Validators.pattern('^[9254][0-9]{7}$')]],
      numC: ['',[Validators.required,Validators.pattern('^[A-Za-z0-9]{3,50}$')]],
      pwd: ['',[Validators.required, Validators.minLength(6)]]
    });
  }
  onSubmit () {

      if(this.signUpForm.valid){
        const client: Client= this.signUpForm.value;
        this.authService.signUp(client).subscribe({
          next: (response: SignUpResponse) =>{
            this.errorMessage=null;
            
            alert(response.message);
            this.router.navigate(['/login']);
          },
          error: (error) => {
            this.errorMessage= error.error?.error || 'Sign up failed';
          }
        });
      } 

    }

}

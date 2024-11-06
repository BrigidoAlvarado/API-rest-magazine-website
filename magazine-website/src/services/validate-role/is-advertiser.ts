import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from "@angular/router";
import { AuthService } from "../auth";

@Injectable({
    providedIn: 'root'
})
export class IsAdvertiser implements CanActivate {

    constructor(private auth: AuthService, private router: Router){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
         if(this.auth.getUserType() != 'anunciante'){
            this.router.navigate(["**"]);
            return false;
         }
         return true;
    }

}
import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { NewAccountComponent } from './user/new-account/new-account.component';
import { LoginComponent } from './login/login.component';
import { AdminHomeComponent} from '../app/admin/admin-home/admin-home.component';
import { AdvertiserHomeComponent} from '../app/advertiser/advertiser-home/advertiser-home.component';
import { EditorHomeComponent} from '../app/editor/editor-home/editor-home.component';
import { SubscriberHomeComponent} from '../app/subscriber/subscriber-home/subscriber-home.component';
import { AdProfileComponent} from "../app/admin/ad-profile/ad-profile.component";

export const routes: Routes = [
    //RUTAS GENERALES
    {
        path: 'login',
        title: 'Iniciar Sesion',
        component: LoginComponent
    },
    {
        path: 'newAccount',
        title: 'Crear Cuenta',
        component: NewAccountComponent
    },
    //RUTAS DEL ADMINISTRADOR
    {
        path: 'admin-home',
        title: 'home',
        component: AdminHomeComponent
    },
    {
        path: 'admin-profile',
        title: 'Perfil',
        component: AdProfileComponent
    },
    //RUTAS DEL ANUNCIANTE
    {
        path: 'advertiser-home',
        title: 'home',
        component: AdvertiserHomeComponent
    },
    //RUTAS DEL EDITOR
    {
        path: 'editor-home',
        title: 'home',
        component: EditorHomeComponent
    },
    //RUTAS DEL SUSCRIPTOR
    {
        path: 'subscriber-home',
        title: 'home',
        component: SubscriberHomeComponent
    },
    //EXTRAS
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: 'login'
    }
    
];

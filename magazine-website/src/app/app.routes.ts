import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { NewAccountComponent } from './user/new-account/new-account.component';
import { LoginComponent } from './login/login.component';
import { AdminHomeComponent} from '../app/admin/admin-home/admin-home.component';
import { AdvertiserHomeComponent} from '../app/advertiser/advertiser-home/advertiser-home.component';
import { EditorHomeComponent} from '../app/editor/editor-home/editor-home.component';
import { SubscriberHomeComponent} from '../app/subscriber/subscriber-home/subscriber-home.component';

export const routes: Routes = [
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
    {
        path: 'admin-home',
        title: 'home',
        component: AdminHomeComponent
    },
    {
        path: 'advertiser-home',
        title: 'home',
        component: AdvertiserHomeComponent
    },
    {
        path: 'editor-home',
        title: 'home',
        component: EditorHomeComponent
    },
    {
        path: 'subscriber-home',
        title: 'home',
        component: SubscriberHomeComponent
    },
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

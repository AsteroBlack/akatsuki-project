import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { LiberationIpComponent } from "./liberation-ip.component";


const routes: Routes= [
    {
        path: '',
        component: LiberationIpComponent,
        data: {
            title: 'LIBEERATION'
        }
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LiberationIpRoutingModule {}
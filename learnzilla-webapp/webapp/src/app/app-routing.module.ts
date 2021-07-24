import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContentGenerationComponent } from './content-generation/content-generation.component';
import { ContentPageComponent } from './content-page/content-page.component';
import { CourseContainerComponent } from './course-container/course-container.component';
import { LoginComponent } from './login/login.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProgramContainerComponent } from './program-container/program-container.component';
import { ProgramCreationComponent } from './program-creation/program-creation.component';
import { RegisterComponent } from './register/register.component';
import { ProgramVideoPlayerComponent } from './program-video-player/program-video-player.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SidenavProgramContentComponent } from './sidenav-program-content/sidenav-program-content.component';
import { LandingComponent } from './landing/landing.component';
import { TrainingContentComponent } from './training-content/training-content.component';
import { ProgramDashboardComponent } from './program-dashboard/program-dashboard.component';
import { ContentViewerComponent } from './content-viewer/content-viewer.component';
import { ProgramDesignComponent } from './program-design/program-design.component';
import { EnrollTraineeComponent} from './enroll-trainee/enroll-trainee.component';
import { ProgramCertificateComponent } from './program-certificate/program-certificate.component';
import { QuestionAnswerSectionComponent } from './question-answer-section/question-answer-section.component';
import { ProgressTestComponent } from './progress-test/progress-test.component';
import { OtpAuthenticationComponent } from './otp-authentication/otp-authentication.component';
import { QuizPlayComponent } from './quiz-play/quiz-play.component';
import { ProgramDetailsComponent } from './program-details/program-details.component';

const routes: Routes = [
  {
    path: 'home',
    component: NavBarComponent,
    children: [
      // {
      //   path:'',component:LandingComponent
      // },
      {
        path: 'programcreation',
        component: ProgramCreationComponent,
      },
      {
        path: 'videoplayer',
        component: ProgramVideoPlayerComponent,
      },
      {
        path:'programdesign',component:ProgramDesignComponent
      },
      {
        path:'videoplayer',component: ProgramVideoPlayerComponent
      },
      {
        path: 'contentgeneration/:title/:domain',
        component: ContentGenerationComponent
      },

      {
        path: 'programcontent/:domain/:intent/:programname/:duration',
        component: SidenavProgramContentComponent,
      },
      {
        path: 'program',
        component: ProgramContainerComponent,
      },
      {
        path: 'content-page',
        component: ContentPageComponent,
      },
      {
        path: 'side-bar',
        component: SidebarComponent,
      },
      {
        path: 'course-container',
        component: CourseContainerComponent,
      },
      {
        path: 'program-dashboard',
        component: ProgramDashboardComponent,
      },
      {
        path: 'program-certificate/:programname',
        component: ProgramCertificateComponent,
      },
      {
        path: 'viewer', component: ContentViewerComponent
      },
      {
        path:'program-dashboard',component:ProgramDashboardComponent
      },
      {
        path:'traineeenroll/:domain',component:EnrollTraineeComponent
      },
      {
        path:'qasection',component: QuestionAnswerSectionComponent
      },
      {
        path:'programdetails',component:ProgramDetailsComponent
      }
    ],
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'training',
    component: TrainingContentComponent,
  },
  {
    path: 'content-viewer',
    component: ContentViewerComponent,
  },
  {
    path: 'progress-test',
    component: ProgressTestComponent,
  },
  {
    path: '',
    component: LandingComponent,
  },
  {
    path:'otpauth',
    component:OtpAuthenticationComponent,
  },
  {
    path:'quiz-play',
    component:QuizPlayComponent,
  },

  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
  },
];

export const appRouting = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}

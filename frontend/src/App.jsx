import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles';
import { CssBaseline } from '@mui/material';
import { Toaster } from 'react-hot-toast';
import theme from './styles/theme';
import { AuthProvider } from './context/AuthContext';
import { AccessibilityProvider } from './context/AccessibilityContext';
import ProtectedRoute from './components/ProtectedRoute';
import DashboardLayout from './layouts/DashboardLayout';

// Auth Pages
import LoginPage from './pages/auth/LoginPage';
import RegisterPage from './pages/auth/RegisterPage';
import ForgotPasswordPage from './pages/auth/ForgotPasswordPage';

// Dashboard Pages
import TeacherDashboard from './pages/teacher/TeacherDashboard';
import StudentDashboard from './pages/student/StudentDashboard';
import AdminDashboard from './pages/admin/AdminDashboard';
import TranslatePage from './pages/student/TranslatePage';
import PlaceholderPage from './pages/PlaceholderPage';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AuthProvider>
        <AccessibilityProvider>
          <Router>
            <Routes>
              {/* Public Routes */}
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/forgot-password" element={<ForgotPasswordPage />} />

              {/* Teacher Routes */}
              <Route path="/teacher" element={
                <ProtectedRoute roles={['ROLE_TEACHER']}>
                  <DashboardLayout />
                </ProtectedRoute>
              }>
                <Route path="dashboard" element={<TeacherDashboard />} />
                <Route path="classrooms" element={<PlaceholderPage title="Classroom Manager" description="Create, edit, and manage your classrooms. Backend APIs: POST/PUT/DELETE /api/classrooms" />} />
                <Route path="lessons" element={<PlaceholderPage title="Lesson Manager" description="Upload and manage lesson content. Backend APIs: CRUD /api/lessons" />} />
                <Route path="quizzes" element={<PlaceholderPage title="Quiz Creator" description="Create quizzes with multiple-choice questions. Backend APIs: CRUD /api/quizzes" />} />
                <Route path="attendance" element={<PlaceholderPage title="Attendance Manager" description="Mark and track student attendance. Backend APIs: POST /api/attendance/mark" />} />
                <Route path="participation" element={<PlaceholderPage title="Participation Dashboard" description="View engagement heatmaps and trends. Backend APIs: GET /api/participation/heatmap" />} />
                <Route path="insights" element={<PlaceholderPage title="AI Insights" description="View AI-generated recommendations for students. Backend APIs: GET /api/ai/recommendations" />} />
              </Route>

              {/* Student Routes */}
              <Route path="/student" element={
                <ProtectedRoute roles={['ROLE_STUDENT']}>
                  <DashboardLayout />
                </ProtectedRoute>
              }>
                <Route path="dashboard" element={<StudentDashboard />} />
                <Route path="classrooms" element={<PlaceholderPage title="My Classrooms" description="View joined classrooms and lesson content." />} />
                <Route path="quizzes" element={<PlaceholderPage title="Quizzes" description="Attempt quizzes and view results. Backend APIs: POST /api/quizzes/attempt" />} />
                <Route path="translate" element={<TranslatePage />} />
              </Route>

              {/* Admin Routes */}
              <Route path="/admin" element={
                <ProtectedRoute roles={['ROLE_ADMIN']}>
                  <DashboardLayout />
                </ProtectedRoute>
              }>
                <Route path="dashboard" element={<AdminDashboard />} />
                <Route path="teachers" element={<PlaceholderPage title="Manage Teachers" description="View and manage all teachers on the platform." />} />
                <Route path="students" element={<PlaceholderPage title="Manage Students" description="View and manage all students on the platform." />} />
                <Route path="classrooms" element={<PlaceholderPage title="All Classrooms" description="Overview of all classrooms. Backend APIs: GET /api/admin/classrooms" />} />
                <Route path="analytics" element={<PlaceholderPage title="Platform Analytics" description="Detailed platform usage analytics." />} />
              </Route>

              {/* Default Redirects */}
              <Route path="/" element={<Navigate to="/login" replace />} />
              <Route path="*" element={<Navigate to="/login" replace />} />
            </Routes>
          </Router>
          <Toaster position="top-right" toastOptions={{
            style: { background: '#1E293B', color: '#F1F5F9', border: '1px solid rgba(148,163,184,0.15)', borderRadius: '12px' },
          }} />
        </AccessibilityProvider>
      </AuthProvider>
    </ThemeProvider>
  );
}

export default App;
